package com.yicj.security.core.properties;

import lombok.Data;
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
@Data
@ConfigurationProperties(prefix = "mini.security")
public class SecurityProperties {
    // 浏览器相关配置
    private BrowserProperties browser = new BrowserProperties() ;
    //验证码配置
    private ValidateCodeProperties code = new ValidateCodeProperties();
    //社交登录配置
    private SocialProperties social = new SocialProperties();
}