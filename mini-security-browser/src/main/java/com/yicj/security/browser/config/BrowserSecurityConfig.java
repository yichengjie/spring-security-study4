package com.yicj.security.browser.config;

import com.yicj.security.core.authentication.FormAuthenticationConfig;
import com.yicj.security.core.authorize.AuthorizeConfigManager;
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

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录相关配置
        formAuthenticationConfig.configure(http);

        http.csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}