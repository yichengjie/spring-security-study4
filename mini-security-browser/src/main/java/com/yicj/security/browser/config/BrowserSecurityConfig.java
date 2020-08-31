package com.yicj.security.browser.config;

import com.yicj.security.core.authentication.FormAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * ClassName: BrowserSecurityConfig
 * Description: TODO(描述)
 * Date: 2020/8/30 23:00
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录相关配置
        formAuthenticationConfig.configure(http);
        // 授权相关配置
        http.authorizeRequests() // 下面都是授权得配置
                .anyRequest() // 任何请求
                .authenticated()  // 都需要身份认证
        .antMatchers().permitAll()
        ;


        http.csrf().disable();
    }
}