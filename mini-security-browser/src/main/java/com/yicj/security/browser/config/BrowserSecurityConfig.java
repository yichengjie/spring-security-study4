package com.yicj.security.browser.config;

import com.yicj.security.core.authentication.FormAuthenticationConfig;
import com.yicj.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yicj.security.core.authorize.AuthorizeConfigManager;
import com.yicj.security.core.properties.SecurityConstants;
import com.yicj.security.core.properties.SecurityProperties;
import com.yicj.security.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

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
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private SecurityProperties securityProperties ;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    // 验证码相关得配置
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig ;
    // rememberMe相关配置

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PersistentTokenRepository persistentTokenRepository ;

    // 社交登录
    @Autowired(required = false)
    private SpringSocialConfigurer miniSocialSecurityConfigurer ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录相关配置
        formAuthenticationConfig.configure(http);
        // 验证码相关配置
        http.apply(validateCodeSecurityConfig) ;
        // 手机验证码配置
        http.apply(smsCodeAuthenticationSecurityConfig) ;
        // 社交登录
        http.apply(miniSocialSecurityConfigurer) ;

        //记住我配置，如果想在'记住我'登录时记录日志，可以注册一个InteractiveAuthenticationSuccessEvent事件的监听器
		http.rememberMe()
            .tokenRepository(persistentTokenRepository)
            .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
            .userDetailsService(userDetailsService) ;

        http.sessionManagement()
            .invalidSessionStrategy(invalidSessionStrategy)
            .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
            .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
            .expiredSessionStrategy(sessionInformationExpiredStrategy) ;

        http.logout()
            .logoutUrl(SecurityConstants.DEFAULT_LOGOUT_URL)
            .logoutSuccessHandler(logoutSuccessHandler)
            .deleteCookies("JSESSIONID") ;

        http.csrf()
            .disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}