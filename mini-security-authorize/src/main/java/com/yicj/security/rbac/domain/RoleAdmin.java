package com.yicj.security.rbac.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 角色用户关系表
 * ClassName: RoleAdmin
 * Description: TODO(描述)
 * Date: 2020/8/31 22:18
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
@Entity
public class RoleAdmin {
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
     * 角色
     */
    @ManyToOne
    private Role role;
    /**
     * 管理员
     */
    @ManyToOne
    private Admin admin;

}