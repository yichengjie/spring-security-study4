package com.yicj.security.core.properties;

import lombok.Data;

/**
 * ClassName: SocialProperties
 * Description: TODO(描述)
 * Date: 2020/9/1 21:27
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class SocialProperties {
    /**
     * 社交登录功能拦截的url
     */
    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();

    private WeixinProperties weixin = new WeixinProperties();
}