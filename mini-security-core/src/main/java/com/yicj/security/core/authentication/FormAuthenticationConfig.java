package com.yicj.security.core.authentication;

import com.yicj.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * ClassName: FormAuthenticationConfig
 * Description: TODO(描述)
 * Date: 2020/8/31 10:57
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Component
public class FormAuthenticationConfig {
    @Autowired
    protected AuthenticationSuccessHandler miniAuthenticationSuccessHandler;
    @Autowired
    protected AuthenticationFailureHandler miniAuthenticationFailureHandler;

    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL)
            .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
            .successHandler(miniAuthenticationSuccessHandler)
            .failureHandler(miniAuthenticationFailureHandler);
    }
}