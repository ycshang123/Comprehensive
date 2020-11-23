package com.soft1851.admin.controller;

import com.soft1851.admin.service.AdminUserService;
import com.soft1851.api.BaseController;
import com.soft1851.api.controller.admin.AdminMsgControllerApi;
import com.soft1851.enums.FaceVerifyType;
import com.soft1851.exception.GraceException;
import com.soft1851.pojo.AdminUser;
import com.soft1851.pojo.bo.AdminLoginBO;
import com.soft1851.pojo.bo.NewAdminBO;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.utils.FaceVerifyUtil;
import com.soft1851.utils.PageGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
public class AdminMsgController extends BaseController implements AdminMsgControllerApi {
    @Autowired
    private AdminUserService adminUserService;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private FaceVerifyUtil faceVerifyUtil;
    @Override
    public GraceResult adminLogin(AdminLoginBO adminLoginBO, HttpServletRequest request, HttpServletResponse response) {
//        查看用户是否存在
        AdminUser adminUser = adminUserService.queryAdminByUsername(adminLoginBO.getUsername());
        if(adminUser == null){
            return GraceResult.errorCustom(ResponseStatusEnum.ADMIN_NOT_EXIT_ERROR);
        }
//        判断密码是否匹配
        boolean isPwdMath = BCrypt.checkpw(adminLoginBO.getPassword(),adminUser.getPassword());
        if(isPwdMath){
            doLoginSettings(adminUser,request,response);
            return GraceResult.ok();
        }else {
            return GraceResult.errorCustom(ResponseStatusEnum.ADMIN_NOT_EXIT_ERROR);
        }
    }

    private void doLoginSettings(AdminUser admin, HttpServletRequest request,HttpServletResponse response){
//        保存token存入redis中
        String token = UUID.randomUUID().toString();
        redis.set(REDIS_ADMIN_TOKEN+":"+ admin.getId(),token);
//        保存admin登录基本token信息到cookie
        SetCookie(request,response,"aToken",token,COOKIE_MONTH);
        SetCookie(request,response,"aId",admin.getId(),COOKIE_MONTH);
        SetCookie(request,response,"aName",admin.getAdminName(),COOKIE_MONTH);
    }

    @Override
    public GraceResult adminIsExist(String username) {
        checkAdminExist(username);
        return GraceResult.ok();
    }
    private void checkAdminExist(String username){
        AdminUser admin = adminUserService.queryAdminByUsername(username);
        if(admin !=null){
            GraceException.display(ResponseStatusEnum.ADMIN_USERNAME_EXIST_ERROR);
        }
    }

    @Override
    public GraceResult addNewAdmin(HttpServletRequest request, HttpServletResponse response, NewAdminBO adminBO) {
//        1、base64部位空，则代表人脸入库，否则需要用户输入密码和确认密码
        if(StringUtils.isBlank(adminBO.getImg64())){
            if(StringUtils.isBlank(adminBO.getPassword()) || StringUtils.isBlank(adminBO.getConfirmPassword())){
                return GraceResult.errorCustom(ResponseStatusEnum.ADMIN_PASSWORD_NULL_ERROR);
            }
        }
//        2、如果密码不为空，则必须判断两次输入一致
        if(StringUtils.isNotBlank(adminBO.getPassword())){
            if(!adminBO.getPassword().equalsIgnoreCase(adminBO.getConfirmPassword())){
                return GraceResult.errorCustom(ResponseStatusEnum.ADMIN_PASSWORD_ERROR);
            }
        }
//        3、验证用户名唯一
        checkAdminExist(adminBO.getUsername());
//        4、调用service存入admin信息
        adminUserService.createAdminUser(adminBO);
        return  GraceResult.ok();
    }

    @Override
    public GraceResult getAdminList(Integer page, Integer pageSize) {
        if(page == null){
            page =COMMON_START_PAGE;
        }
        if(pageSize == null){
            pageSize = COMMON_PAGE_SIZE;
        }
        PageGridResult result = adminUserService.queryAdminList(page,pageSize);
        return GraceResult.ok(result);
    }

    @Override
    public GraceResult adminLogout(String adminId, HttpServletRequest request, HttpServletResponse response) {
//        1、从redis中删除admin的会话cookie
        redis.del(REDIS_ADMIN_TOKEN+":"+adminId);
//        2、从cookie中清理admin的相关信息
        deleteCookie(request,response,"aToken");
        deleteCookie(request,response,"aId");
        deleteCookie(request,response,"aName");
        return GraceResult.ok();
    }

    @Override
    public GraceResult updateAdmin(HttpServletRequest request, HttpServletResponse response, NewAdminBO newAdminBO) {
        adminUserService.updateAdmin(newAdminBO.getUsername(), newAdminBO.getFaceId());
        return GraceResult.ok(newAdminBO);
    }

    @Override
    public GraceResult adminFaceLogin(AdminLoginBO adminLoginBO, HttpServletRequest request, HttpServletResponse response) {
//        判断用户名和人脸信息不能为空
        if(StringUtils.isBlank(adminLoginBO.getUsername())){
            return GraceResult.errorCustom(ResponseStatusEnum.ADMIN_USERNAME_NULL_ERROR);
        }
        System.out.println(">>>>>>>>>>>>>>>"+adminLoginBO);
        String tempFace64 = adminLoginBO.getImg64();
        if(StringUtils.isBlank(tempFace64)){
            return GraceResult.errorCustom(ResponseStatusEnum.ADMIN_FACE_NULL_ERROR);
        }
//        从数据库中根据username查询出faceId
        AdminUser admin = adminUserService.queryAdminByUsername(adminLoginBO.getUsername());
        String adminFaceId = admin.getFaceId();
        System.out.println(adminFaceId);
        if(StringUtils.isBlank(adminFaceId)){
            return GraceResult.errorCustom(ResponseStatusEnum.ADMIN_FACE_LOGIN_ERROR);
        }
//        请求文件服务，根据faceId获得人脸数据的base64数据
        String fileServerUrl ="http://admin.sn.com:8004/fs/readFace64?faceId="+adminFaceId;
//        得到的结果是封装的结果
        ResponseEntity<GraceResult> responseEntity = restTemplate.getForEntity(fileServerUrl,GraceResult.class);
        GraceResult bodyResult = responseEntity.getBody();
        assert  bodyResult !=null;
        String base64 = (String) bodyResult.getData();
//        调用阿里ai进行人脸对比识别，判断可信度，实现人脸登录
        boolean result = faceVerifyUtil.faceVerify(FaceVerifyType.BASE64.type,tempFace64,base64,60);
        if(!result){
            return  GraceResult.errorCustom(ResponseStatusEnum.ADMIN_FACE_LOGIN_ERROR);
        }
//        admin登录后的数据设置，redis和cookie
        doLoginSettings(admin,request,response);
        return GraceResult.ok();
    }
}
