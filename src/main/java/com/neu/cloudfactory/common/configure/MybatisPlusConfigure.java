package com.neu.cloudfactory.common.configure;

import com.neu.cloudfactory.common.interceptor.DataPermissionInterceptor;
import com.neu.cloudfactory.common.interceptor.DesensitizationInterceptor;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author wxd
 */
@MapperScan("com.neu.cloudfactory.*.mapper")
@Configuration(proxyBeanMethods = false)
public class MybatisPlusConfigure {
    /**
     * 注册数据权限
     */
    @Bean
    @Order(0)
    public DataPermissionInterceptor dataPermissionInterceptor() {
        return new DataPermissionInterceptor();
    }

    /**
     * 数据脱敏
     */
    @Bean
    @Order(-1)
    public DesensitizationInterceptor desensitizationInterceptor() {
        return new DesensitizationInterceptor();
    }

    /**
     * 注册分页插件
     */
    @Bean
    @Order(-2)
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}
