package com.neu.cloudfactory.common.annotation;

import com.neu.cloudfactory.common.entity.Strings;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author wxd
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Publisher {
    @AliasFor(annotation = Component.class)
    String value() default Strings.EMPTY;
}
