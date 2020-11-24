package com.soft1851.admin.repository;

import com.soft1851.pojo.mo.FriendLinkMO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 友链操作
 */
public interface FriendLinkRepository  extends MongoRepository<FriendLinkMO,String> {


    /**
     * 根据是否删除字段查询所有友链
     * @param isDelete
     * @return
     */
    List<FriendLinkMO> getAllByIsDelete(Integer isDelete);

}
