package com.yicj.security.rbac.domain;

import com.yicj.security.rbac.dto.ResourceInfo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *  需要控制权限的资源，以业务人员能看懂的name呈现.实际关联到一个或多个url上。
 *
 *  树形结构。
 * ClassName: Resource
 * Description: TODO(描述)
 * Date: 2020/8/31 22:21
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
@Entity
public class Resource {
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
     * 资源名称，如xx菜单，xx按钮
     */
    private String name;
    /**
     * 资源链接
     */
    private String link;
    /**
     * 图标
     */
    private String icon;
    /**
     * 资源类型
     */
    @Enumerated(EnumType.STRING)
    private ResourceType type;
    /**
     * 实际需要控制权限的url
     */
    @ElementCollection
    private Set<String> urls;
    /**
     * 父资源
     */
    @ManyToOne
    private Resource parent;
    /**
     * 序号
     */
    private int sort;
    /**
     * 子资源
     */
    @OneToMany(mappedBy = "parent")
    @OrderBy("sort ASC")
    private List<Resource> children = new ArrayList<>();


    public ResourceInfo toTree(Admin admin) {
        ResourceInfo result = new ResourceInfo();
        BeanUtils.copyProperties(this, result);
        Set<Long> resourceIds = admin.getAllResourceIds();

        List<ResourceInfo> children = new ArrayList<ResourceInfo>();
        for (Resource child : getChildren()) {
            if(StringUtils.equals(admin.getUsername(), "admin") ||
                    resourceIds.contains(child.getId())){
                children.add(child.toTree(admin));
            }
        }
        result.setChildren(children);
        return result;
    }

    public void addChild(Resource child) {
        children.add(child);
        child.setParent(this);
    }

}