package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.Role;
import com.daji.jyhx.repository.RoleRepository;
import com.daji.jyhx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 大稽
 * @date2018/11/1915:44
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleList;
    }
}
