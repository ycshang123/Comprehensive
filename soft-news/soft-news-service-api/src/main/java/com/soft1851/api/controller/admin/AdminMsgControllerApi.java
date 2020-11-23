package com.soft1851.api.controller.admin;

/**
 * 管理员API接口
 */

import com.soft1851.pojo.bo.AdminLoginBO;
import com.soft1851.pojo.bo.NewAdminBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "管理员维护", tags = {"管理员维护controller"})
@RequestMapping("adminMsg")
public interface AdminMsgControllerApi {

    /**
     * 管理员API接口
     *
     * @param adminLoginBO
     * @param request
     * @param response
     * @return
     */
    @PostMapping("adminLogin")
    @ApiOperation(value = "管理员登录", notes = "管理员登录", httpMethod = "POST")
    GraceResult adminLogin(@RequestBody AdminLoginBO adminLoginBO, HttpServletRequest request, HttpServletResponse response);

    @PostMapping("adminIsExist")
    @ApiOperation(value = "查询管理员是否存在", notes = "查询管理人员是否存在", httpMethod = "POST")
    GraceResult adminIsExist(@RequestParam String username);


    @PostMapping("addNewAdmin")
    @ApiOperation(value = "添加新的管理人员", notes = "添加新的管理人员", httpMethod = "POST")
    GraceResult addNewAdmin(HttpServletRequest request, HttpServletResponse response, @RequestBody NewAdminBO adminBO);


    @ApiOperation(value = "查询admin列表", notes = "查询admin列表", httpMethod = "POST")
    @PostMapping("/getAdminList")
    GraceResult getAdminList(
            @ApiParam(name = "page", value = "查询下一页的第几页")
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页查询每一页显示的条数")
            @RequestParam Integer pageSize
    );

    @PostMapping("adminLogout")
    @ApiOperation(value = "管理员注销",notes = "管理员注销",httpMethod = "POST")
    GraceResult adminLogout(@RequestParam String adminId,HttpServletRequest request,HttpServletResponse response);

    @ApiOperation(value = "修改管理员信息", notes = "修改管理员信息", httpMethod = "POST")
    @PostMapping("updateAdmin")
    GraceResult updateAdmin(HttpServletRequest request,
                            HttpServletResponse response, @RequestBody NewAdminBO newAdminBO);

    @PostMapping("adminFaceLogin")
    @ApiOperation(value = "管理员人脸登录",notes = "管理员人脸登录",httpMethod = "POST")
    GraceResult adminFaceLogin(@RequestBody AdminLoginBO adminLoginBO, HttpServletRequest request, HttpServletResponse response);
}
