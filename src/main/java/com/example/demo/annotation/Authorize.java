package com.example.demo.annotation;

import com.example.demo.utils.AuthorizeUtil;

import java.lang.annotation.*;

/**
 * @author Gilbert
 * @date 2021/4/16 16:07
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Authorize {
    AuthorizeUtil.Character value();
}
