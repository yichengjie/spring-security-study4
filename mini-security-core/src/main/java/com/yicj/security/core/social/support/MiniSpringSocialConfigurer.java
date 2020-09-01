package com.yicj.security.core.social.support;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * ClassName: MiniSpringSocialConfigurer
 * Description: TODO(描述)
 * Date: 2020/9/1 22:59
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class MiniSpringSocialConfigurer extends SpringSocialConfigurer {
    private String filterProcessesUrl;

    private SocialAuthenticationFilterPostProcessor postProcessor;

    public MiniSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        if (postProcessor != null) {
            postProcessor.process(filter);
        }
        return (T) filter;
    }

}