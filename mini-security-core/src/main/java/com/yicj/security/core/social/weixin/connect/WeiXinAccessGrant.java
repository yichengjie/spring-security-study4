package com.yicj.security.core.social.weixin.connect;

import lombok.Getter;
import lombok.Setter;
import org.springframework.social.oauth2.AccessGrant;

/**
 * ClassName: WeiXinAccessGrant
 * Description: TODO(描述)
 * Date: 2020/9/2 21:09
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Setter
@Getter
public class WeiXinAccessGrant extends AccessGrant {

    private String openId;

    public WeiXinAccessGrant() {
        super("");
    }

    public WeiXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

}