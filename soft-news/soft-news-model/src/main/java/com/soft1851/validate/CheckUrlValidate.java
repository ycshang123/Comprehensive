package com.soft1851.validate;


import com.soft1851.utils.UrlUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验连接注解
 */
public class CheckUrlValidate implements ConstraintValidator<com.soft1851.validate.CheckUrl,String> {
    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
        return UrlUtil.verifyUrl(url.trim());
    }
}
