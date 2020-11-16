package com.soft1851.user.service.impl;

import com.soft1851.pojo.AppUser;
import com.soft1851.user.mapper.AppUserMapper;
import com.soft1851.user.service.UserService;
import com.soft1851.utils.DateUtil;
import com.soft1851.utils.redis.RedisOperator;
import lombok.RequiredArgsConstructor;
import org.n3r.idworker.Sid;
import org.n3r.idworker.utils.DesensitizationUtil;
import org.n3r.idworker.utils.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    public final AppUserMapper appUserMapper;
    public final RedisOperator redis;
    @Resource
    private Sid sid;
    public static final String REDIS_USER_INFO = "redis_user_info";
    private static final String USER_FACE0 = "https://wx1.sinaimg.cn/mw690/00669kenly1gkelnlelz2j3300200hdu.jpg";

    @Override
    public AppUser queryMobileIsExist(String mobile) {
        Example userExample = new Example(AppUser.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("mobile", mobile);
        return appUserMapper.selectOneByExample(userExample);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppUser createUser(String mobile) {
//        若分库分表，数据库表主键id必须保证全局(全库)唯一性，不得重复
        String userId = sid.nextShort();
//        构建用户对象
        AppUser user = AppUser.builder()
                .id(userId)
                .mobile(mobile)
                .face(USER_FACE0)
                .birthday(DateUtil.stringToDate("2000-01-01"))
                .totalIncome(0)
                .activeStatus(UserStatus.INACTIVE.type)
                .nickname("用户" + DesensitizationUtil.commonDisplay(mobile))
                .createdTime(new Date())
                .updatedTime(new Date())
                .build();
        appUserMapper.insert(user);
        return user;


    }
}
