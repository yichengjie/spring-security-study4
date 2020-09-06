package com.yicj.security.app.client.validate.code.impl;

import com.yicj.security.core.validate.code.ValidateCodeRepository;
import com.yicj.security.core.validate.model.ValidateCode;
import com.yicj.security.core.validate.model.ValidateCodeType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;


@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {
    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {

    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return null;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {

    }
}
