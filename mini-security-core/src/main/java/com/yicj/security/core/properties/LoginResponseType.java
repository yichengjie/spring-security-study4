package com.yicj.security.core.properties;

/**
 * 认证成功后的响应方式
 * ClassName: LoginResponseType
 * Description: TODO(描述)
 * Date: 2020/8/31 10:34
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public enum  LoginResponseType {
    /**
     * 跳转
     */
    REDIRECT,
    /**
     * 返回json
     */
    JSON
}