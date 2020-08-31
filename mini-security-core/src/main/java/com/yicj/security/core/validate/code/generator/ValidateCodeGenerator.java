package com.yicj.security.core.validate.code.generator;

import com.yicj.security.core.validate.code.model.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 */
public interface ValidateCodeGenerator {

    /**
     * 生成校验码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}
