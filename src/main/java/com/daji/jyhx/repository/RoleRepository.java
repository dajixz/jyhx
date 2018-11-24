package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 大稽
 * @date2018/11/1915:01
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByRoleDescription(String roleDescription);
}
