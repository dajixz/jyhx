package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.Role;
import com.daji.jyhx.entity.Teacher;
import com.daji.jyhx.repository.TeacherRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

///**
// * @author 大稽
// * @date2018/10/1115:20
// */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户的id: {}", username);
        Teacher teacher = teacherRepository.findTeacherByTeacherId(username);
        String roleStr = "";
        if(teacher!=null){
            for (Role role :teacher.getRoleList()) {
                roleStr += role.getRoleName()+",";
            }
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleStr);
            teacher.setAuthorities(grantedAuthorities);
        }else{
            throw new UsernameNotFoundException("用户名或密码错误！");
        }

        return teacher;
    }
}
