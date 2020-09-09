package com.yicj.security.core.social;

import com.yicj.security.core.social.support.SocialUserInfo;
import org.springframework.social.connect.Connection;

public abstract class SocialController {

    //根据Connection信息构建SocialUserInfo
    protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadImg(connection.getImageUrl());
        return userInfo;
    }
}
