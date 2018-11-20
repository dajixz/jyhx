package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.Role;
import com.daji.jyhx.entity.Teacher;
import com.daji.jyhx.repository.RoleRepository;
import com.daji.jyhx.repository.TeacherRepository;
import com.daji.jyhx.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 大稽
 * @date2018/11/1712:53
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<Teacher> getTeacherList(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.Direction.ASC, "teacherId");
        Page<Teacher> teacherList = teacherRepository.findAll(pageable);
        return teacherList;
    }

    @Override
    public Page<Teacher> getTeachers(Integer page, String teacherName) {
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.Direction.ASC, "teacherId");
        Page<Teacher> teachers = teacherRepository.getTeachersByTeacherNameContaining(pageable, teacherName);
        return teachers;
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        String teacherId = teacher.getTeacherId();
        Teacher teacherByTeacherId = teacherRepository.findTeacherByTeacherId(teacherId);
        if (teacherByTeacherId != null) {
            return null;
        }
        String _password = teacher.getTeacherPassword();
        teacher.setTeacherPassword(passwordEncoder.encode(_password));
        int role_id = teacher.getRole();
        Role role = roleRepository.findById(role_id).get();
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        teacher.setRoleList(roleList);
        Teacher save = teacherRepository.save(teacher);
        return save;
    }

    @Override
    public Teacher getTeacherByTeacherId(String teacherId) {
        return teacherRepository.findTeacherByTeacherId(teacherId);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        int[] roles = teacher.getRoles();
        List<Role> roleList = new ArrayList<>();
        if (roles != null) {
            for (int i = 0; i < roles.length; i++) {
                int roleId = roles[i];
                if (roleId != 0) {
                    Role role = roleRepository.findById(roleId).get();
                    roleList.add(role);
                }
            }
        }
        teacher.setRoleList(roleList);
        Teacher update = teacherRepository.save(teacher);
        return update;
    }

    @Override
    public List<Teacher> saveBathTeachers(List<Teacher> teachers,String teacherSchoolId) {
        for(Teacher teacher :teachers){
            teacher.setTeacherSchoolId(teacherSchoolId);
            //初始密码123456
            teacher.setTeacherPassword(passwordEncoder.encode("123456"));
        }
        return teacherRepository.saveAll(teachers);
    }
}
