package com.soft1851.api.controller.admin;

/**
 * 管理员API接口
 */

import com.soft1851.pojo.bo.AdminLoginBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "管理员维护",tags = {"管理员维护controller"})
@RequestMapping("adminMsg")
public interface AdminMsgControllerApi {

    /**
     * 管理员API接口
     * @param adminLoginBO
     * @param request
     * @param response
     * @return
     */
    @PostMapping("adminLogin")
    @ApiOperation(value = "管理员登录",notes = "管理员登录",httpMethod = "POST")
    GraceResult adminLogin(@RequestBody AdminLoginBO adminLoginBO, HttpServletRequest request, HttpServletResponse response);
}