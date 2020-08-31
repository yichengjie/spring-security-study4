package com.yicj.security.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名{}",username);
        //根据用户名查找用户信息
        return buildUser(username);
    }

    private User buildUser(String userId) {
        // 根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        String roles = "ROLE_USER" ;
        if ("admin".equals(userId)){
            roles = "ROLE_USER,ROLE_ADMIN" ;
        }
        return new User(userId, "123",
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
    }
}