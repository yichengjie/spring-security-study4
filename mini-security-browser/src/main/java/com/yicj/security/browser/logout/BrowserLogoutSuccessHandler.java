package com.yicj.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicj.security.core.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: BrowserLogoutSuccessHandler
 * Description: TODO(描述)
 * Date: 2020/8/31 14:24
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
public class BrowserLogoutSuccessHandler implements LogoutSuccessHandler {
    private String signOutSuccessUrl;
    private ObjectMapper objectMapper = new ObjectMapper();

    public BrowserLogoutSuccessHandler(String signOutSuccessUrl) {
        this.signOutSuccessUrl = signOutSuccessUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        log.info("logout success ...");
        if (StringUtils.isBlank(signOutSuccessUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("logout success ...")));
        } else {
            response.sendRedirect(signOutSuccessUrl);
        }
    }
}