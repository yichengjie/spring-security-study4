package com.yicj.security.core.validate.code.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * ClassName: ValidateCodeException
 * Description: TODO(描述)
 * Date: 2020/8/31 15:33
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}