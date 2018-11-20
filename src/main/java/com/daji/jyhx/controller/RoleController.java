package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Role;
import com.daji.jyhx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 大稽
 * @date2018/11/1915:46
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/getRoles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
}
