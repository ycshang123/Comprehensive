package com.soft1851.user.controller;

import com.soft1851.api.BaseController;
import com.soft1851.api.controller.user.UserControllerApi;
import com.soft1851.pojo.AppUser;
import com.soft1851.pojo.bo.UpdateUserInfoBO;
import com.soft1851.pojo.vo.AppUserVO;
import com.soft1851.pojo.vo.UserAccountInfoVo;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.user.mapper.AppUserMapper;
import com.soft1851.user.service.UserService;
import com.soft1851.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController extends BaseController implements UserControllerApi {
    private final AppUserMapper appUserMapper;
    private final UserService userService;

    @Override
    public Object getAllUsers() {
        return GraceResult.ok(appUserMapper.selectAll());
    }

    @Override
    public GraceResult getUserInfo(String userId) {
//        判断不能为空
        if (StringUtils.isBlank(userId)) {
            return GraceResult.errorCustom(ResponseStatusEnum.UN_LOGIN);
        }
//        根据useId查询用户
        AppUser user = getUser(userId);
//        设置VO——需要展示的信息
        UserAccountInfoVo accountInfoVo = new UserAccountInfoVo();
//        属性拷贝
        BeanUtils.copyProperties(user, accountInfoVo);
        return GraceResult.ok(accountInfoVo);
    }

    private AppUser getUser(String userId) {
//        查询redis中是否包含用户信息，如果包含则查询redis返回，如果不包含则查询数据库
        String userJson = redis.get(REDIS_USER_INFO + ":" + userId);
        AppUser appUser;
        if (StringUtils.isNotBlank(userJson)) {
            appUser = JsonUtil.jsonToPojo(userJson, AppUser.class);
        } else {
            appUser = userService.getUser(userId);
//            由于用户信息不怎么变动，这类信息数据不会去查询数据库，完全可以把用户信息存入redis
//            哪怕修改信息，也不会立马体现，这也是弱一致性，在这里有过期时间，到时间用户信息就会更新到页面显示
//            基本信息是属于数据一致性优先级比较低的，用户眼里的主要以内容为主，至于是谁发的，一般不会过的关注
            redis.set(REDIS_USER_INFO + ":" + userId, JsonUtil.objectToJson(appUser), 1);
        }
        return appUser;
    }

    @Override
    public GraceResult updateUserInfo(@Valid UpdateUserInfoBO userInfoBO, BindingResult result) {
//        判断BindingResultS是否保存错误信息，如果有，直接return
        if (result.hasErrors()) {
            Map<String, String> errorMap = getErrors(result);
            return GraceResult.errorMap(errorMap);
        }
//        执行更新用户信息操作
        return GraceResult.ok();
    }

    @Override
    public GraceResult getUserBasicInfo(String userId) {
//        判断不能为空
        if (StringUtils.isBlank(userId)) {
            return GraceResult.errorCustom(ResponseStatusEnum.UN_LOGIN);
        }
//        查询userId
        AppUser user = getUser(userId);
//        信息脱敏，设置不显示
        AppUserVO userVO = new AppUserVO();
        BeanUtils.copyProperties(user, userVO);
        return GraceResult.ok(userVO);

    }

}
