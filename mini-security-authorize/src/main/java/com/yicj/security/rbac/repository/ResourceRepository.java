package com.yicj.security.rbac.repository;

import com.yicj.security.rbac.domain.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends RbacRepository<Resource>{
    Resource findByName(String name);
}
