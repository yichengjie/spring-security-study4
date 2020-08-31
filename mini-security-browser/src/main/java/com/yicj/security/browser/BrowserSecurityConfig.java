package com.yicj.security.browser.config;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic() // 用表单登录来进行身份认证
                .and()
            .authorizeRequests() // 下面都是授权得配置
                .anyRequest() // 任何请求
                .authenticated()  // 都需要身份认证
        ;
    }
}