package com.yicj.security.browser.session;

import com.yicj.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * ClassName: MiniExpiredSessionStrategy
 * Description: TODO(描述)
 * Date: 2020/8/31 14:38
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class MiniExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    public MiniExpiredSessionStrategy(SecurityProperties securityProperties) {
        super(securityProperties);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}