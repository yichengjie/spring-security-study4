package com.yicj.security.core.validate.code;

import com.yicj.security.core.validate.model.ValidateCode;
import com.yicj.security.core.validate.model.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * ClassName: ValidateCodeRepository
 * Description: TODO(描述)
 * Date: 2020/8/31 15:13
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);
    /**
     * 获取验证码
     * @param request
     * @param validateCodeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);
    /**
     * 移除验证码
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);
}