package com.soft1851.admin.controller;

import com.soft1851.admin.service.AdminUserService;
import com.soft1851.api.BaseController;
import com.soft1851.api.controller.admin.AdminMsgControllerApi;
import com.soft1851.pojo.AdminUser;
import com.soft1851.pojo.bo.AdminLoginBO;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCrypt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
public class AdminMsgController extends BaseController implements AdminMsgControllerApi {
    @Autowired
    private AdminUserService adminUserService;
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

}
