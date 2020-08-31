package com.yicj.security.core.properties;

import lombok.Data;

/**
 * ClassName: BrowserProperties
 * Description: TODO(描述)
 * Date: 2020/8/31 10:26
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class BrowserProperties {
    /**
     * session管理配置项
     */
    private SessionProperties session = new SessionProperties();
    /**
     * 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
     */
    private String signInPageUrl = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
    /**
     * 社交登录，如果需要用户注册，跳转的页面
     */
    private String signUpPageUrl = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL ;

    /**
     * 退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。
     */
    private String signOutUrl;

    /**
     * 登录响应的方式，默认是json
     */
    private LoginResponseType signInResponseType = LoginResponseType.JSON;
    /**
     * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上。
     *
     * 只在signInResponseType为REDIRECT时生效
     */
    private String singInSuccessUrl = "/yicj-index.html";

    /**
     * '记住我'功能的有效时间，默认1小时
     */
    private int rememberMeSeconds = 3600;

}