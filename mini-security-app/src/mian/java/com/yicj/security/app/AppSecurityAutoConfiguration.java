package com.yicj.security.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

@ComponentScan("com.yicj.security")
public class AppSecurityAutoConfiguration  extends AuthorizationServerConfigurerAdapter {

}