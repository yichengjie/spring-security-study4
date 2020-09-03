package com.yicj.security.rbac;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Component("rbacService")
public class RbacServiceImpl implements RbacService{

    private AntPathMatcher pathMatcher = new AntPathMatcher() ;
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false ;
        if (principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
            // 获取用户所拥有权限的所有url
            Set<String> urls = new HashSet<>() ;

            for (String url: urls){
                if (pathMatcher.match(url, request.getRequestURI())){
                    hasPermission = true ;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
