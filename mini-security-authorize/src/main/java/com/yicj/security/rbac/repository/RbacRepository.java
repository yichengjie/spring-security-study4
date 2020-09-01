package com.yicj.security.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * ClassName: RbacRepository
 * Description: TODO(描述)
 * Date: 2020/8/31 22:14
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@NoRepositoryBean
public interface RbacRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}