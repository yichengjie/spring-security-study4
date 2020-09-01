package com.yicj.security.rbac.dto;

import jdk.management.resource.ResourceType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ResourceInfo
 * Description: TODO(描述)
 * Date: 2020/8/31 22:25
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class ResourceInfo {

    /**
     * 资源ID
     *
     * @since 1.0.0
     */
    private Long id;
    /**
     *
     */
    private Long parentId;
    /**
     * 资源名
     *
     * @since 1.0.0
     */
    private String name;
    /**
     * 资源链接
     *
     * @since 1.0.0
     */
    private String link;
    /**
     * 图标
     */
    private String icon;
    /**
     * 资源类型
     */
    private ResourceType type;
    /**
     * 子节点
     */
    private List<ResourceInfo> children = new ArrayList<>();
}