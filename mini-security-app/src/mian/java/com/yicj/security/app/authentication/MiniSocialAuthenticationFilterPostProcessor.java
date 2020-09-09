package com.yicj.security.app.authentication;

import com.yicj.security.core.social.support.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class MiniSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    @Qualifier("miniAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler miniAuthenticationSuccessHandler ;

    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setAuthenticationSuccessHandler(miniAuthenticationSuccessHandler);
    }
}
