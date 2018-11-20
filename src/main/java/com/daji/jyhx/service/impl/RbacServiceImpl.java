package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.Permission;
import com.daji.jyhx.entity.Role;
import com.daji.jyhx.entity.Teacher;
import com.daji.jyhx.repository.TeacherRepository;
import com.daji.jyhx.service.RbacService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 大稽
 * @date2018/10/1519:25
 */
@Service("rbacService")
public class RbacServiceImpl implements RbacService {
    
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();

        boolean hasPermission = false;

        if(principal instanceof UserDetails){
            List<String> urls = new ArrayList<>();
            String username = ((UserDetails) principal).getUsername();
            Teacher teacher = teacherRepository.findTeacherByTeacherId(username);
            List<Role> roles = teacher.getRoleList();
            for (Role role: roles) {
                List<Permission> permissions = role.getPermissions();
                for(Permission permission:permissions){
                    String url = permission.getUrl();
                    if(url.contains(",")){
                        String[] s_url = url.split(",");
                        urls.addAll(Arrays.asList(s_url));
                        continue;
                    }
                    urls.add(url);
                }
            }
            for(String url:urls){
                if(antPathMatcher.match((url),request.getRequestURI())){
                    hasPermission = true;
                    break;
                }
            }
        }
        
        return hasPermission;
    }
}
