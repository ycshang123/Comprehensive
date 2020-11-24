package com.soft1851.admin.service;

import com.soft1851.pojo.mo.FriendLinkMO;

public interface FriendLinkService {


    /**
     * 新增或者更新友链链接
     * @param friendLinkMO
     */
    void saveOrUpdateFriendLink(FriendLinkMO friendLinkMO);
}
