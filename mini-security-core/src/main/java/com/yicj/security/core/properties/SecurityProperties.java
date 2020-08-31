package com.yicj.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: SecurityProperties
 * Description: TODO(描述)
 * Date: 2020/8/31 10:25
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@ConfigurationProperties(prefix = "yicj.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties() ;

}