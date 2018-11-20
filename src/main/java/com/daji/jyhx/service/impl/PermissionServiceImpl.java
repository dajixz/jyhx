package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.Permission;
import com.daji.jyhx.repository.PermissionRepository;
import com.daji.jyhx.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 大稽
 * @date2018/11/2015:44
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public List<Permission> getPermissionList() {
        return permissionRepository.findAll();
    }
}
