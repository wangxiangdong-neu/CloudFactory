package com.neu.cloudfactory.common.annotation;

import com.neu.cloudfactory.common.condition.OnRedisCacheCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author wxd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnRedisCacheCondition.class)
public @interface ConditionOnRedisCache {
}
