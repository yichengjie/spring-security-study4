package com.yicj.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码的处理逻辑
 * ClassName: ValidateCodeProcessor
 * Description: TODO(描述)
 * Date: 2020/8/31 15:28
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface ValidateCodeProcessor {
    /**
     * 创建校验码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void create(ServletWebRequest servletWebRequest) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);

}