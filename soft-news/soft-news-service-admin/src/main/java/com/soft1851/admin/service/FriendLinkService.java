package com.soft1851.admin.service;

import com.soft1851.pojo.mo.FriendLinkMO;

import java.util.List;

public interface FriendLinkService {


    /**
     * 新增或者更新友链链接
     * @param friendLinkMO
     */
    void saveOrUpdateFriendLink(FriendLinkMO friendLinkMO);

    /**
     * 查询友情链接
     * @return
     */
    List<FriendLinkMO> queryAllFriendLinkList();
}
