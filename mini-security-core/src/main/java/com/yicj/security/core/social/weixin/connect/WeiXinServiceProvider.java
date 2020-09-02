package com.yicj.security.core.social.weixin.connect;

import com.yicj.security.core.social.weixin.api.WeiXinApi;
import com.yicj.security.core.social.weixin.api.WeiXinApiImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * 微信的OAuth2流程处理器的提供器，供spring social的connect体系调用
 * ClassName: WeixinServiceProvider
 * Description: TODO(描述)
 * Date: 2020/9/2 21:23
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class WeiXinServiceProvider extends AbstractOAuth2ServiceProvider<WeiXinApi> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeiXinServiceProvider(String appId, String appSecret) {
        super(new WeiXinOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public WeiXinApi getApi(String accessToken) {
        return new WeiXinApiImpl(accessToken);
    }
}