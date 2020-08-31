package com.yicj.security.core.authorize;

import com.yicj.security.core.properties.SecurityConstants;
import com.yicj.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 * ClassName: DefaultAuthorizeConfigProvider
 * Description: TODO(描述)
 * Date: 2020/8/31 12:21
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Component
@Order(Integer.MIN_VALUE)
public class DefaultAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL,
            SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
            SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
            securityProperties.getBrowser().getSignInPageUrl(),
            securityProperties.getBrowser().getSignUpPageUrl(),
            securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
            "/error"
        ).permitAll();

        if (StringUtils.isNotBlank(securityProperties.getBrowser().getSignOutUrl())) {
            config.antMatchers(securityProperties.getBrowser().getSignOutUrl()).permitAll();
        }
        return false;
    }
}