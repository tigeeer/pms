package com.wangjx.pms.annotation.own;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/3
 * Time: 13:55
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface OwnParam {

    String value() default "";
}
