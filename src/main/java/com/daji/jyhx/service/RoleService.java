package com.daji.jyhx.service;

import com.daji.jyhx.entity.Role;

import java.util.List;

/**
 * @author 大稽
 * @date2018/11/1915:43
 */
public interface RoleService {
    List<Role> getRoles();
    Role getRoleByRoleId(Integer roleId);
    Role updateRole(Role role);
}
