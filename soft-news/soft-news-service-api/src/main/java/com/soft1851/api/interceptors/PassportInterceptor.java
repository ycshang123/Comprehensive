package com.soft1851.api.interceptors;

import com.soft1851.exception.GraceException;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.utils.IpUtil;
import com.soft1851.utils.redis.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    public RedisOperator redis;
    public static final String MOBILE_SMSCODE = "mobile:smscode";

    /**
     * 拦截请求，访问controller之前
     *
     * @param request  请求
     * @param response 响应
     * @param handler  拦截
     * @return boolean
     * @throws Exception 抛出异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        获得用户ip
        String userIp = IpUtil.getRequestIp(request);
//        检查redis中是否存在这个ip
        boolean keyIsExist = redis.keyIsExist(MOBILE_SMSCODE + ":" + userIp);
        if (keyIsExist) {
            GraceException.display(ResponseStatusEnum.SMS_NEED_WAIT_ERROR);
//            请求拦截
            return false;
        }
//        true: 请求通过验证，放行
        return true;
    }

    /**
     * 请求访问到controller之后，渲染视图之前
     *
     * @param request      请求
     * @param response     响应
     * @param handler      拦截
     * @param modelAndView 视图
     * @throws Exception 抛出异常
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求访问到controller之后，渲染视图之前
     * @param request 请求
     * @param response 响应
     * @param handler 拦截
     * @param ex 视图
     * @throws Exception 抛出异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
