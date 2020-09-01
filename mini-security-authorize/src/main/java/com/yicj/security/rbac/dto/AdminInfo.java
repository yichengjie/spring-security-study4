package com.yicj.security.rbac.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: AdminInfo
 * Description: TODO(描述)
 * Date: 2020/8/31 22:24
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class AdminInfo {
    private Long id;
    /**
     * 角色id
     */
    @NotBlank(message = "角色id不能为空")
    private Long roleId;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
}