package com.yicj.security.rbac.authorize;

import com.yicj.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;


@Order
@Component
public class RbacAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        // 这里anyRequest应该最后执行所以order为Integer.MAX_VALUE, 而且整个项目应该只有一个anyRequest，
        // 否则前面的会被后面的覆盖, 所以这里返回true
        config.anyRequest().access("@rbacService.hasPermission(request,authentication)") ;
        return true;
    }
}
