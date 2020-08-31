package com.yicj.security.core.validate.code.sender.impl;

import com.yicj.security.core.validate.code.sender.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: DefaultSmsCodeSender
 * Description: TODO(描述)
 * Date: 2020/8/31 15:58
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        log.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
        log.info("向手机"+mobile+"发送短信验证码"+code);
    }
}