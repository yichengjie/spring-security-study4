package com.yicj.security.rbac.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息
 * ClassName: Role
 * Description: TODO(描述)
 * Date: 2020/8/31 22:19
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
@Entity
public class Role {
    /**
     * 数据库表主键
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 审计日志，记录条目创建时间，自动赋值，不需要程序员手工赋值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;
    /**
     * 角色名称
     */
    @Column(length = 20, nullable = false)
    private String name;
    /**
     * 角色拥有权限的资源集合
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private Set<RoleResource> resources  = new HashSet<>();
    /**
     * 角色的用户集合
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private Set<RoleAdmin> admins = new HashSet<>();
}