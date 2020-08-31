package com.yicj.security.core.validate.code;

import com.yicj.security.core.validate.model.ValidateCode;
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
