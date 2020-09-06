package com.yicj.security.demo.controller;

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

/**
 * ClassName: UserController
 * Description: TODO(����)
 * Date: 2020/9/2 15:45
 *
 * @author yicj(626659321 @ qq.com)
 * �޸ļ�¼
 * @version ��Ʒ�汾��Ϣ yyyy-mm-dd ����(����) �޸���Ϣ
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/register")
    public String register(User user, HttpServletRequest request) {
        //������ע���û����ǰ��û��������õ�һ���û�Ψһ��ʶ��
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
        //appSingUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
        return "success" ;
    }

    @GetMapping("/me")
    public Object getCurrentUser(Authentication user){
        return user;
    }
}