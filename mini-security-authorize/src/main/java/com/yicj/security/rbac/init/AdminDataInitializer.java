package com.yicj.security.rbac.init;

import com.yicj.security.rbac.domain.*;
import com.yicj.security.rbac.repository.AdminRepository;
import com.yicj.security.rbac.repository.ResourceRepository;
import com.yicj.security.rbac.repository.RoleAdminRepository;
import com.yicj.security.rbac.repository.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AdminDataInitializer extends AbstractDataInitializer {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private RoleAdminRepository roleAdminRepository;

	@Autowired
	protected ResourceRepository resourceRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idea.core.spi.initializer.DataInitializer#getIndex()
	 */
	@Override
	public Integer getIndex() {
		return Integer.MIN_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idea.core.spi.initializer.AbstractDataInitializer#doInit()
	 */
	@Override
	protected void doInit() {
		initResource();
		Role role = initRole();
		initAdmin(role);
	}

	/**
	 * 初始化用户数据
	 */
	private void initAdmin(Role role) {
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("123456"));
		adminRepository.save(admin);

		RoleAdmin roleAdmin = new RoleAdmin();
		roleAdmin.setRole(role);
		roleAdmin.setAdmin(admin);
		roleAdminRepository.save(roleAdmin);
	}

	/**
	 * 初始化角色数据
	 * @return
	 */
	private Role initRole() {
		Role role = new Role();
		role.setName("超级管理员");
		roleRepository.save(role);
		return role;
	}

	/**
	 * 初始化菜单数据
	 */
	protected void initResource() {
		Resource root = createRoot("根节点");

		createResource("首页", "", "home", root);

		Resource menu1 = createResource("平台管理", "", "desktop", root);

//		createResource("资源管理", "resource", "", menu1);
		createResource("角色管理", "role", "", menu1);
		createResource("管理员管理", "admin", "", menu1);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idea.core.spi.initializer.AbstractDataInitializer#isNeedInit()
	 */
	@Override
	protected boolean isNeedInit() {
		return adminRepository.count() == 0;
	}


	protected Resource createRoot(String name) {
		Resource node = new Resource();
		node.setName(name);
		resourceRepository.save(node);
		return node;
	}

	protected Resource createResource(String name, Resource parent) {
		return createResource(name, null, null, parent);
	}

	protected Resource createResource(String name, String link, String iconName, Resource parent) {
		Resource node = new Resource();
		node.setName(name);
		node.setIcon(iconName);
		node.setParent(parent);
		node.setType(ResourceType.MENU);
		if (StringUtils.isNotBlank(link)) {
			node.setLink(link + "Manage");
			Set<String> urls = new HashSet<>();
			urls.add(link + "Manage");
			urls.add("/" + link + "/**");
			node.setUrls(urls);
		}
		resourceRepository.save(node);
		return node;
	}
}