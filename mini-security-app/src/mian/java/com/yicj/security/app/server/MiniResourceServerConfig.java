package com.yicj.security.app.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Slf4j
@Configuration
@EnableResourceServer
public class MiniResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "authorizationServer" ;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId(RESOURCE_ID) ;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 只有/user/me端点作为资源服务器的资源
        log.info("ResourceServerConfig中配置HttpSecurity对象执行");
        http.requestMatchers()
                .antMatchers("/user/me")
                .and()
            .authorizeRequests()
                .anyRequest()
                .authenticated()
        ;
        super.configure(http);
    }
}
