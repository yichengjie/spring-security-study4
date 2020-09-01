package com.yicj.security.rbac.service.impl;

import com.yicj.security.rbac.domain.Admin;
import com.yicj.security.rbac.domain.RoleAdmin;
import com.yicj.security.rbac.dto.AdminCondition;
import com.yicj.security.rbac.dto.AdminInfo;
import com.yicj.security.rbac.repository.AdminRepository;
import com.yicj.security.rbac.repository.RoleAdminRepository;
import com.yicj.security.rbac.repository.RoleRepository;
import com.yicj.security.rbac.repository.spec.AdminSpec;
import com.yicj.security.rbac.repository.support.QueryResultConverter;
import com.yicj.security.rbac.service.AdminService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ClassName: AdminServiceImpl
 * Description: TODO(描述)
 * Date: 2020/8/31 22:55
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleAdminRepository roleAdminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AdminInfo create(AdminInfo adminInfo) {

        Admin admin = new Admin();
        BeanUtils.copyProperties(adminInfo, admin);
        admin.setPassword(passwordEncoder.encode("123456"));
        adminRepository.save(admin);
        adminInfo.setId(admin.getId());

        createRoleAdmin(adminInfo, admin);

        return adminInfo;
    }

    @Override
    public AdminInfo update(AdminInfo adminInfo) {

        Admin admin = adminRepository.findById(adminInfo.getId()).get();
        BeanUtils.copyProperties(adminInfo, admin);

        createRoleAdmin(adminInfo, admin);

        return adminInfo;
    }

    //创建角色用户关系数据。
    private void createRoleAdmin(AdminInfo adminInfo, Admin admin) {
        if(CollectionUtils.isNotEmpty(admin.getRoles())){
            roleAdminRepository.deleteAll(admin.getRoles());
        }
        RoleAdmin roleAdmin = new RoleAdmin();
        roleAdmin.setRole(roleRepository.getOne(adminInfo.getRoleId()));
        roleAdmin.setAdmin(admin);
        roleAdminRepository.save(roleAdmin);
    }

    @Override
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public AdminInfo getInfo(Long id) {
        Admin admin = adminRepository.findById(id).get();
        AdminInfo info = new AdminInfo();
        BeanUtils.copyProperties(admin, info);
        return info;
    }

    @Override
    public Page<AdminInfo> query(AdminCondition condition, Pageable pageable) {
        Page<Admin> admins = adminRepository.findAll(new AdminSpec(condition), pageable);
        return QueryResultConverter.convert(admins, AdminInfo.class, pageable);
    }

}