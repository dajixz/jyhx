package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Role;
import com.daji.jyhx.service.RoleService;
import com.daji.jyhx.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/updateRole")
    public ResponseVo updateRole(Role role){
        ResponseVo responseVo = new ResponseVo();
        Role update = roleService.updateRole(role);
        if (update != null) {
            responseVo.setCode(200);
            responseVo.setData(update);
        } else {
            responseVo.setCode(403);
        }
        return responseVo;
    }
}
