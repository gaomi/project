package com.company.project;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Chen
 * @created 2019-07-31-11:21.
 */
public class TunnelAppInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //return super.configure(builder);
        //Application的类名
        return builder.sources(TunnelAppApplication.class);
    }
}
