package com.yicj.security.core.social.weixin.api;

/**
 * 微信API调用接口
 * ClassName: Weixin
 * Description: TODO(描述)
 * Date: 2020/9/2 21:05
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface WeiXin {

    WeiXinUserInfo getUserInfo(String openId);

}