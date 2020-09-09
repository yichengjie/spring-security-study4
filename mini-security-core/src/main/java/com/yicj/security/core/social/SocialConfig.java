package com.yicj.security.core.social;

import com.yicj.security.core.properties.SecurityProperties;
import com.yicj.security.core.social.support.MiniSpringSocialConfigurer;
import com.yicj.security.core.social.support.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.util.Assert;

import javax.sql.DataSource;

/**
 * 社交登录配置主类
 * ClassName: SocialConfig
 * Description: TODO(描述)
 * Date: 2020/9/1 20:54
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;
    @Autowired
    private SecurityProperties securityProperties ;
    // filter的特殊处理器，写这里在@Bean中使用时可能为null
    //@Autowired(required = false)
    //private SocialAuthenticationFilterPostProcessor filterPostProcessor;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(
            ConnectionFactoryLocator locator) {
        JdbcUsersConnectionRepository repository =
                new JdbcUsersConnectionRepository(dataSource, locator, Encryptors.noOpText());
        //repository.setTablePrefix("mini_");
        if(connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    //这个不知道干啥用的，spring2.x不配置会报错，1.x不需要配置
    @Override
    public UserIdSource getUserIdSource() {
        return new SecurityContextUserIdSource();
    }

    //用来处理注册流程的工具类
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
            getUsersConnectionRepository(connectionFactoryLocator)) {
        };
    }
    // 最重要的也是也是最后一步
    // 添加spring-social的filter过滤器
    @Bean("miniSocialSecurityConfigurer")
    public SpringSocialConfigurer miniSocialSecurityConfigurer(@Autowired(required = false) SocialAuthenticationFilterPostProcessor filterPostProcessor){
        // 自定义spring-social登录的地址，SocialAuthenticationFilter中默认为‘/auth’
        // 这里自定义为‘/qqLogin’
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        MiniSpringSocialConfigurer configurer = new MiniSpringSocialConfigurer(filterProcessesUrl);
        // 设置注册页面地址
        configurer.signupUrl(securityProperties.getBrowser().getSignUpPageUrl());
        //设置filter的特殊处理器
        configurer.setFilterPostProcessor(filterPostProcessor);
        return  configurer;
    }


    @Bean
    public ConnectController connectController(
            ConnectionFactoryLocator factoryLocator, ConnectionRepository repository){
        ConnectController connectController = new ConnectController(factoryLocator, repository) ;
        return connectController ;
    }


    // 这个不知道干啥用的，spring2.x不配置会报错，1.x不需要配置
    private static class SecurityContextUserIdSource implements UserIdSource {
        @Override
        public String getUserId() {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            Assert.state(authentication != null,
                    "Unable to get a " + "ConnectionRepository: no user signed in");
            return authentication.getName();
        }
    }
}