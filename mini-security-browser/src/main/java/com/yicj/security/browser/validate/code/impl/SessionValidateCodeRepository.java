package com.yicj.security.browser.validate.code.impl;

import com.yicj.security.browser.session.support.HttpSessionSessionStrategy;
import com.yicj.security.browser.session.support.SessionStrategy;
import com.yicj.security.core.validate.code.model.ValidateCode;
import com.yicj.security.core.validate.code.repo.ValidateCodeRepository;
import com.yicj.security.core.validate.code.model.ValidateCodeType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * ClassName: SessionValidateCodeRepository
 * Description: TODO(描述)
 * Date: 2020/8/31 15:17
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /* (non-Javadoc)
     * @see com.imooc.security.core.validate.code.ValidateCodeRepository#save(org.springframework.web.context.request.ServletWebRequest, com.imooc.security.core.validate.code.ValidateCode, com.imooc.security.core.validate.code.ValidateCodeType)
     */
    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(request, validateCodeType), code);
    }

    /**
     * 构建验证码放入session时的key
     *
     * @param request
     * @return
     */
    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }

    /* (non-Javadoc)
     * @see com.imooc.security.core.validate.code.ValidateCodeRepository#get(org.springframework.web.context.request.ServletWebRequest, com.imooc.security.core.validate.code.ValidateCodeType)
     */
    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(request, validateCodeType));
    }

    /* (non-Javadoc)
     * @see com.imooc.security.core.validate.code.ValidateCodeRepository#remove(org.springframework.web.context.request.ServletWebRequest, com.imooc.security.core.validate.code.ValidateCodeType)
     */
    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request, codeType));
    }
}