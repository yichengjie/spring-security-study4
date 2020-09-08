package com.yicj.security.app;

import com.yicj.security.core.authentication.FormAuthenticationConfig;
import com.yicj.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yicj.security.core.authorize.AuthorizeConfigManager;
import com.yicj.security.core.validate.ValidateCodeSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// spring-security的主配置类
@Slf4j
@Configuration
@EnableWebSecurity(debug = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig ;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;
    // 验证码相关得配置
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("SecurityConfiguration 中配置HttpSecurity对象执行");
        //表单登录相关配置
        formAuthenticationConfig.configure(http);
        // 验证码相关配置
        http.apply(validateCodeSecurityConfig) ;
        // 手机验证码配置
        http.apply(smsCodeAuthenticationSecurityConfig) ;
        // csrf关闭
        http.csrf().disable();
        // authorizeRequests自定义配置
        authorizeConfigManager.config(http.authorizeRequests());
    }

    // 密码模式时使用AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }
}
