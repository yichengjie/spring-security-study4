package com.yicj.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicj.security.core.properties.SecurityProperties;
import com.yicj.security.core.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: AbstractSessionStrategy
 * Description: TODO(描述)
 * Date: 2020/8/31 14:33
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
public class AbstractSessionStrategy {
    /**
     * 跳转的url
     */
    private String destinationUrl;
    /**
     * 系统配置信息
     */
    private SecurityProperties securityProperties;
    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * 跳转前是否创建新的session
     */
    private boolean createNewSession = true;

    private ObjectMapper objectMapper = new ObjectMapper();


    public AbstractSessionStrategy(SecurityProperties securityProperties) {
        String invalidSessionUrl = securityProperties.getBrowser().getSession().getSessionInvalidUrl();
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        Assert.isTrue(StringUtils.endsWithIgnoreCase(invalidSessionUrl, ".html"), "url must end with '.html'");
        this.destinationUrl = invalidSessionUrl;
        this.securityProperties = securityProperties;
    }


    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("session invalid ...");
        if (createNewSession) {
            request.getSession();
        }
        String sourceUrl = request.getRequestURI();
        String targetUrl;
        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            if(StringUtils.equals(sourceUrl, securityProperties.getBrowser().getSignInPageUrl())
                    || StringUtils.equals(sourceUrl, securityProperties.getBrowser().getSignOutUrl())){
                targetUrl = sourceUrl;
            }else{
                targetUrl = destinationUrl;
            }
            log.info("redirect url :" + targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            Object result = buildResponseContent(request);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }

    }

    /**
     * @param request
     * @return
     */
    protected Object buildResponseContent(HttpServletRequest request) {
        String message = "session already invalid ...";
        if (isConcurrency()) {
            message = message + "，It may be caused by concurrent login";
        }
        return new SimpleResponse(message);
    }

    /**
     * session失效是否是并发导致的
     *
     * @return
     */
    protected boolean isConcurrency() {
        return false;
    }

    /**
     * Determines whether a new session should be created before redirecting (to
     * avoid possible looping issues where the same session ID is sent with the
     * redirected request). Alternatively, ensure that the configured URL does
     * not pass through the {@code SessionManagementFilter}.
     *
     * @param createNewSession
     *            defaults to {@code true}.
     */
    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}