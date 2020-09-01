package com.yicj.security.rbac.repository;

import com.yicj.security.rbac.domain.Admin;
import org.springframework.stereotype.Repository;

/**
 * ClassName: AdminRepository
 * Description: TODO(描述)
 * Date: 2020/8/31 22:15
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Repository
public interface AdminRepository extends RbacRepository<Admin>{
    Admin findByUsername(String username);
}