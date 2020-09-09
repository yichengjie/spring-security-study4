package com.yicj.security.app.social;

import com.yicj.security.core.properties.SecurityConstants;
import com.yicj.security.core.social.support.MiniSpringSocialConfigurer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(StringUtils.equals(beanName, "miniSocialSecurityConfigurer")){
            MiniSpringSocialConfigurer config = (MiniSpringSocialConfigurer)bean;
            config.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
            return config;
        }
        return bean;
    }
}
