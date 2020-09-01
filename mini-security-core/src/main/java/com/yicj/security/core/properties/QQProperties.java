package com.yicj.security.core.properties;

import lombok.Data;

/**
 * ClassName: QQProperties
 * Description: TODO(描述)
 * Date: 2020/9/1 21:25
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class QQProperties {
    //Application id.
    private String appId;
    //Application secret.
    private String appSecret;
    //第三方id，用来决定发起第三方登录的url，默认是 qq。
    private String providerId = "qq";
}