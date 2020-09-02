package com.yicj.security.core.social.weixin.config;

import com.yicj.security.core.properties.SecurityProperties;
import com.yicj.security.core.properties.WeiXinProperties;
import com.yicj.security.core.social.weixin.connect.WeiXinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 微信登录配置
 * ClassName: WeiXinAutoConfiguration
 * Description: TODO(描述)
 * Date: 2020/9/2 21:24
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Configuration
@ConditionalOnProperty(prefix = "mini.security.social.weixin", name = "app-id")
public class WeiXinAutoConfiguration extends SocialConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    protected ConnectionFactory<?> createConnectionFactory() {
        WeiXinProperties weiXinConfig = securityProperties.getSocial().getWeixin();
        return new WeiXinConnectionFactory(weiXinConfig.getProviderId(), weiXinConfig.getAppId(),
                weiXinConfig.getAppSecret());
    }
}