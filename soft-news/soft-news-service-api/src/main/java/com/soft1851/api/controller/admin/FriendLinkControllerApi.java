package com.soft1851.api.controller.admin;


import com.soft1851.pojo.bo.SaveFriendLinkBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(value = "首页友情链接维护",tags = {"首页友情链接维护controller"})
@RequestMapping("friendLinkMsg")
public interface FriendLinkControllerApi {


    @ApiOperation(value = "新增或者修改友情链接",notes = "新增或者修改友情链接",httpMethod = "POST")
    @PostMapping("/saveOrUpdateFriendLink")
    GraceResult saveOrUpdateFriendLink(@RequestBody @Valid SaveFriendLinkBO saveFriendLinkBO, BindingResult result);

    @PostMapping("getFriendLinkList")
    @ApiOperation(value = "查询友情链接列表",notes = "查询友情链接列表",httpMethod = "POST")
    GraceResult getFriendLinkList();

}
