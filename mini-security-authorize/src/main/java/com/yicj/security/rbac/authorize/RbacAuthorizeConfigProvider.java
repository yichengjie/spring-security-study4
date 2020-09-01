package com.yicj.security.rbac.authorize;

import com.yicj.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * ClassName: RbacAuthorizeConfigProvider
 * Description: TODO(描述)
 * Date: 2020/8/31 22:33
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Component
@Order(Integer.MAX_VALUE)
public class RbacAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(HttpMethod.GET, "/fonts/**").permitAll()
            .antMatchers(HttpMethod.GET,
                    "/**/*.html",
                    "/admin/me",
                    "/resource").authenticated()
            .anyRequest()
            .access("@rbacService.hasPermission(request, authentication)");
        return true;
    }
}