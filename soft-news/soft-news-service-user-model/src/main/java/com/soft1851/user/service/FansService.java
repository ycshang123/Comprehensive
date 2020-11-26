package com.soft1851.user.service;

public interface FansService {
    /**
     * 查询当前用户是否关注作者
     * @param writerId
     * @param fanId
     * @return
     */
    boolean isMeFollowThisWriter(String writerId,String fanId);


    /**
     * 关注作者，称为粉丝
     * @param writerId
     * @param fanId
     */
    void follow(String writerId,String fanId);

    /**
     * 取消关注
     * @param writerId
     * @param fanId
     */
    void unfollow(String writerId,String fanId);
}
