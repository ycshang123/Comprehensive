package com.soft1851.user.service.impl;

import com.soft1851.api.service.BaseService;
import com.soft1851.pojo.AppUser;
import com.soft1851.pojo.Fans;
import com.soft1851.user.mapper.FansMapper;
import com.soft1851.user.service.FansService;
import com.soft1851.user.service.UserService;
import com.soft1851.utils.redis.RedisOperator;
import lombok.RequiredArgsConstructor;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FansServiceImpl  extends BaseService implements FansService {

    private final FansMapper fansMapper;
    private final Sid sid;
    private final UserService userService;
    private final RedisOperator redis;
    @Override
    public boolean isMeFollowThisWriter(String writerId, String fanId) {
        Fans fans = new Fans();
        fans.setFanId(fanId);
        fans.setWriterId(writerId);
        int count = fansMapper.selectCount(fans);
        return count >0;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void follow(String writerId, String fanId) {
        AppUser fanInfo = userService.getUser(fanId);
        String fanPkId = sid.nextShort();
        Fans fans = new Fans();
        fans.setId(fanPkId);
        fans.setFanId(fanId);
        fans.setFace(fanInfo.getFace());
        fans.setWriterId(writerId);
        fans.setFanNickname(fanInfo.getNickname());
        fans.setProvince(fanInfo.getProvince());
        fans.setSex(fanInfo.getSex());
        fansMapper.insert(fans);
        redis.increment(REDIS_WRITER_FANS_COUNTS+":"+writerId,1);
        redis.increment(REDIS_MY_FOLLOW_COUNTS+":"+fanId,1);

    }
}
