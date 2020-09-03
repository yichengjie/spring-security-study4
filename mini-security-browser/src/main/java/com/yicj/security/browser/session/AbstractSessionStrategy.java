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
    //跳转的url
    private String destinationUrl;
    //系统配置信息
    private SecurityProperties securityProperties;
    //重定向策略
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    //跳转前是否创建新的session
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
        // 获取之前的页面路径
        String sourceUrl = request.getRequestURI();
        String targetUrl;
        //如果路径是以html结尾,说明需要做页面跳转
        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            //如果之前地址和登录地址一样，或则和退出登录地址一样，则最终地址为原来地址
            if(StringUtils.equals(sourceUrl, securityProperties.getBrowser().getSignInPageUrl())
                    || StringUtils.equals(sourceUrl, securityProperties.getBrowser().getSignOutUrl())){
                targetUrl = sourceUrl;
            }else{
                //非登录/登出地址外则跳转到配置的session过期地址上
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


    protected Object buildResponseContent(HttpServletRequest request) {
        String message = "session already invalid ...";
        if (isConcurrency()) {
            message = message + "，It may be caused by concurrent login";
        }
        return new SimpleResponse(message);
    }

    //session失效是否是并发导致的
    protected boolean isConcurrency() {
        return false;
    }
}