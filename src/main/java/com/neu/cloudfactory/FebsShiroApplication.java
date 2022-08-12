package com.neu.cloudfactory;

import com.neu.cloudfactory.common.annotation.FebsShiro;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author wxd
 */
@FebsShiro
public class FebsShiroApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FebsShiroApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
