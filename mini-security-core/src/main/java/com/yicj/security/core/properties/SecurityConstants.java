package com.yicj.security.core.properties;

/**
 * ClassName: SecurityConstants
 * Description: TODO(描述)
 * Date: 2020/8/31 10:31
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface SecurityConstants {
    /**
     * 默认登录页面
     */
    String DEFAULT_SIGN_IN_PAGE_URL = "/mini-signIn.html";
    /**
     * 默认注册页面
     */
    String DEFAULT_SIGN_UP_PAGE_URL = "/mini-signUp.html";
    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
    /**
     * 当请求需要身份认证时，默认跳转的url
     */
    String DEFAULT_UN_AUTHENTICATION_URL = "/authentication/require";
    /**
     * 默认的用户名密码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";
    /**
     * 默认的OPENID登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_OPENID = "/authentication/openid";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
    /**
     * openid参数名
     */
    String DEFAULT_PARAMETER_NAME_OPENID = "openId";
    /**
     * providerId参数名
     */
    String DEFAULT_PARAMETER_NAME_PROVIDERID = "providerId";
    /**
     * session失效默认的跳转地址
     */
    String DEFAULT_SESSION_INVALID_URL = "/mini-session-invalid.html";

    /**
     * 默认退出登录地址
     */
    String DEFAULT_LOGOUT_URL = "/logout" ;
    /**
     * 获取第三方用户信息的url
     */
    String DEFAULT_SOCIAL_USER_INFO_URL = "/social/user";
}