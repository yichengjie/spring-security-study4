package com.yicj.security.core.social;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.UserIdSource;

/**
 * ClassName: CurrentUserHolder
 * Description: TODO(描述)
 * Date: 2020/9/1 22:01
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class CurrentUserHolder implements UserIdSource {

    @Override
    public String getUserId() {
        String userId = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if(context != null) {
            Object principal = context.getAuthentication().getPrincipal();
            if(principal instanceof UserDetails) {
                userId = ((UserDetails)principal).getUsername();
            }
        }
        if(StringUtils.isBlank(userId)) {
            userId = RandomStringUtils.randomNumeric(4);
        }
        return userId;
    }
}