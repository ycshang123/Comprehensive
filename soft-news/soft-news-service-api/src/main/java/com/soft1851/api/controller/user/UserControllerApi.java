package com.soft1851.api.controller.user;

import com.soft1851.pojo.bo.UpdateUserInfoBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Api(value = "用户信息相关Controller", tags = {"用户信息相关Controller"})
@RequestMapping("user")
public interface UserControllerApi {
    /**
     * 获取所有用户
     *
     * @return
     */
    @ApiOperation(value = "获得所有用户信息", notes = "获得所有用户信息", httpMethod = "POST")
    @PostMapping("/all")
    Object getAllUsers();


    @ApiOperation(value = "获得用户账户信息", notes = "获得用户账户信息", httpMethod = "POST")
    @PostMapping("/userInfo")
    GraceResult getUserInfo(@RequestParam String userId);

    @PostMapping("/updateUserInfo")
    @ApiOperation(value = "完善用户信息", notes = "完善用户信息", httpMethod = "POST")
    GraceResult updateUserInfo(@RequestBody @Valid UpdateUserInfoBO userInfoBO, BindingResult result);


    @PostMapping("/getUserInfo")
    @ApiOperation(value = "获得用户基础信息", notes = "获得用户基本信息", httpMethod = "POST")
    GraceResult getUserBasicInfo(@RequestParam String userId);


}
