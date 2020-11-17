package com.soft1851.user.controller;

import com.soft1851.api.BaseController;
import com.soft1851.api.controller.user.UserControllerApi;
import com.soft1851.pojo.AppUser;
import com.soft1851.pojo.bo.UpdateUserInfoBO;
import com.soft1851.pojo.vo.UserAccountInfoVo;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.user.mapper.AppUserMapper;
import com.soft1851.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController  extends BaseController implements UserControllerApi  {
    private final AppUserMapper appUserMapper;
    private final UserService userService;

    @Override
    public Object getAllUsers() {
        return GraceResult.ok(appUserMapper.selectAll());
    }

    @Override
    public GraceResult getUserInfo(String userId) {
//        判断不能为空
        if (StringUtils.isBlank(userId)) {
            return GraceResult.errorCustom(ResponseStatusEnum.UN_LOGIN);
        }
//        根据useId查询用户
        AppUser user = getUser(userId);
//        设置VO——需要展示的信息
        UserAccountInfoVo accountInfoVo = new UserAccountInfoVo();
//        属性拷贝
        BeanUtils.copyProperties(user, accountInfoVo);
        return GraceResult.ok(accountInfoVo);
    }

    private AppUser getUser(String userId) {
        return userService.getUser(userId);
    }

    @Override
    public GraceResult updateUserInfo(@Valid UpdateUserInfoBO userInfoBO, BindingResult result) {
//        判断BindingResultS是否保存错误信息，如果有，直接runtun
        if(result.hasErrors()){
            Map<String,String> errorMap = getErrors(result);
            return GraceResult.errorMap(errorMap);
        }
//        执行更新用户信息操作
        return GraceResult.ok();
    }
}
