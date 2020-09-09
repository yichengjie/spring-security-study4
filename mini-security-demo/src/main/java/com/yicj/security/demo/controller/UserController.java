package com.yicj.security.demo.controller;

import com.yicj.security.app.social.AppSingUpUtils;
import com.yicj.security.demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {

   /* @Autowired
    private ProviderSignInUtils providerSignInUtils;*/
    @Autowired
    private AppSingUpUtils appSingUpUtils ;

    @PostMapping("/register")
    public String register(User user, HttpServletRequest request) {
        String userId = user.getUsername();
        appSingUpUtils.doPostSignUp(userId, new ServletWebRequest(request));
        //appSingUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
        return "success" ;
    }

    @GetMapping("/me")
    public Object getCurrentUser(Authentication user){
        return user;
    }
}