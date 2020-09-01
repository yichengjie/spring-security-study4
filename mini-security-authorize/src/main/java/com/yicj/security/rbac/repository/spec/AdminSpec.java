package com.yicj.security.rbac.repository.spec;

import com.yicj.security.rbac.domain.Admin;
import com.yicj.security.rbac.dto.AdminCondition;
import com.yicj.security.rbac.repository.support.RbacSpecification;
import com.yicj.security.rbac.repository.support.QueryWrapper;

/**
 * ClassName: AdminSpec
 * Description: TODO(描述)
 * Date: 2020/8/31 22:43
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class AdminSpec extends RbacSpecification<Admin, AdminCondition> {

    public AdminSpec(AdminCondition condition) {
        super(condition);
    }

    @Override
    protected void addCondition(QueryWrapper<Admin> queryWraper) {
        addLikeCondition(queryWraper, "username");
    }
}