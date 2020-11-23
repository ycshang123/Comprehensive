package com.soft1851.admin.service;

import com.soft1851.pojo.AdminUser;
import com.soft1851.pojo.bo.NewAdminBO;
import com.soft1851.utils.PageGridResult;

public interface AdminUserService {
    /**
     * 获得管理员用户信息
     * @param username
     * @return
     */
    AdminUser queryAdminByUsername(String username);

    /**
     * 新增管理员
     * @param newAdminBO
     */
    void createAdminUser(NewAdminBO newAdminBO);

    /**
     * 分页查询管理员
     * @param page
     * @param pageSize
     * @return
     */
    PageGridResult queryAdminList(Integer page,Integer pageSize);

    /**
     * 修改指定管理员的faceId
     *
     * @param username
     * @param faceId
     */
    void updateAdmin(String username, String faceId);
}
