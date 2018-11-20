package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Permission;
import com.daji.jyhx.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 大稽
 * @date2018/11/2015:46
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/getPermissions")
    public List<Permission> getPermissions(){
        return permissionService.getPermissionList();
    }
}
