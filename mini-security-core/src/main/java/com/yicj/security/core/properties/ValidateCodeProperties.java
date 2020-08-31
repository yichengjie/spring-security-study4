package com.yicj.security.core.properties;

import lombok.Data;

/**
 * ClassName: ValidateCodeProperties
 * Description: TODO(描述)
 * Date: 2020/8/31 15:47
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class ValidateCodeProperties {
    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();

    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
}