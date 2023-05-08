package com.luucungquan.securityfirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luucungquan.securityfirst.models.Role;
import com.luucungquan.securityfirst.models.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRolename(RoleName rolename);
}
