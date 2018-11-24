package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.Permission;
import com.daji.jyhx.entity.Role;
import com.daji.jyhx.repository.PermissionRepository;
import com.daji.jyhx.repository.RoleRepository;
import com.daji.jyhx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 大稽
 * @date2018/11/1915:44
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Role> getRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleList;
    }

    @Override
    public Role getRoleByRoleId(Integer roleId) {
        return roleRepository.findById(roleId).get();
    }

    @Override
    public Role updateRole(Role role) {
        List<Integer> permissions = role.getPermissions();
        List<Permission> permissionList = new ArrayList<>();
        if (permissions != null) {
            for (Integer permissionId : permissions) {
                if (permissionId != null) {
                    Permission permission = permissionRepository.findById(permissionId).get();
                    permissionList.add(permission);
                }
            }
        }
        role.setPermissionList(permissionList);
        Role update = roleRepository.save(role);
        return update;
    }

}
