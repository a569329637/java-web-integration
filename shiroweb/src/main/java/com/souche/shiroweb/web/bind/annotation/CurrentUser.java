package com.souche.shiroweb.web.bind.annotation;

import com.souche.shiroweb.constants.Constants;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/10/21.
 */
/**
 * <p>绑定当前登录的用户</p>
 * <p>不同于@ModelAttribute</p>
 *
 * @author Zhang Kaitao
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default Constants.CURRENT_USER;

}
