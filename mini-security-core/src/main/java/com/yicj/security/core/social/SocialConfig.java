package com.yicj.security.core.social;

import com.yicj.security.core.properties.SecurityProperties;
import com.yicj.security.core.social.support.MiniSpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

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

    //用来处理注册流程的工具类
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
            getUsersConnectionRepository(connectionFactoryLocator)) {
        };
    }


    // 最重要的也是也是最后一步
    // 添加spring-social的filter过滤器
    @Bean
    public SpringSocialConfigurer miniSocialSecurityConfigurer(){
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        MiniSpringSocialConfigurer socialConfigurer = new MiniSpringSocialConfigurer(filterProcessesUrl);
        return  socialConfigurer;
    }
}