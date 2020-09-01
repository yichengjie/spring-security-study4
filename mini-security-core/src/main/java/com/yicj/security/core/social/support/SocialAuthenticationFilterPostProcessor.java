package com.yicj.security.core.social.support;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * ClassName: SocialAuthenticationFilterPostProcessor
 * Description: TODO(描述)
 * Date: 2020/9/1 23:00
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}