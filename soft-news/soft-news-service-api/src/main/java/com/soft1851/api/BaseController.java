package com.soft1851.api;

import com.soft1851.utils.redis.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    public RedisOperator redis;

    public static final String MOBILE_SMSCODE="moble:smscode";
}
