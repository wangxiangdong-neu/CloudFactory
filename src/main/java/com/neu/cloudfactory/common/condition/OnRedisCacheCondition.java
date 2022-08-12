package com.neu.cloudfactory.common.condition;

import com.neu.cloudfactory.common.properties.FebsProperties;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.NonNull;

/**
 * @author wxd
 */
public class OnRedisCacheCondition implements Condition {

    @Override
    public boolean matches(@NonNull ConditionContext context, @NonNull AnnotatedTypeMetadata metadata) {
        return Boolean.parseBoolean(context.getEnvironment().getProperty(FebsProperties.ENABLE_REDIS_CACHE));
    }
}
