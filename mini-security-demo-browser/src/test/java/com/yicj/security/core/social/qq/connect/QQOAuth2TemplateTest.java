package com.yicj.security.core.social.qq.connect;

import org.junit.Before;
import org.junit.Test;
import org.springframework.social.oauth2.AccessGrant;

public class QQOAuth2TemplateTest {
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
    private QQOAuth2Template auth2Template ;

    @Before
    public void before(){
        String clientId = "100550231" ;
        String clientSecret = "69b6ab57b22f3c2fe6a6149274e3295e" ;
        auth2Template = new QQOAuth2Template(clientId, clientSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN) ;
    }

    @Test
    public void exchangeForAccess(){
        String authorizationCode ="455B4F844367C9F4ACD2A8FF83AF0D43" ;
        String returnToUrl = "http://www.pinzhi365.com/qqLogin/callback.do" ;
        AccessGrant accessGrant = auth2Template.exchangeForAccess(authorizationCode, returnToUrl, null);
        System.out.println(accessGrant);
    }
}
