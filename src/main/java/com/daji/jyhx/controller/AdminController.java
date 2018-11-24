package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Permission;
import com.daji.jyhx.entity.Role;
import com.daji.jyhx.entity.Teacher;
import com.daji.jyhx.service.ExcelService;
import com.daji.jyhx.service.PermissionService;
import com.daji.jyhx.service.RoleService;
import com.daji.jyhx.service.TeacherService;
import com.daji.jyhx.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 大稽
 * @date2018/11/117:43
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ExcelService excelService;

    @GetMapping("/admin-role")
    public String adminRoleView() {
        return "admin/admin-role";
    }

    @GetMapping("/admin-edit/{teacherId}")
    public String adminEditView(@PathVariable("teacherId") String teacherId, Model model) {
        Teacher teacher = teacherService.getTeacherByTeacherId(teacherId);
        List<Role> roleList = teacher.getRoleList();
        List<Integer> flagList = new ArrayList<>();
        for (Role role : roleList) {
            int roleId = role.getRoleId();
            flagList.add(roleId);
        }
        List<Role> roles = roleService.getRoles();
        for (Role role : roles) {
            int roleId = role.getRoleId();
            if (flagList.contains(roleId)) {
                role.setFlag(true);
            }
        }
        model.addAttribute("teacher", teacher);
        model.addAttribute("roles", roles);
        return "admin/admin-edit";
    }

    @GetMapping("/admin-list")
    public String adminListView() {
        return "admin/admin-list";
    }

    @GetMapping("/admin-rule")
    public String adminRuleView() {
        return "admin/admin-rule";
    }

    @GetMapping("/admin-cate")
    public String adminCateView() {
        return "admin/admin-cate";
    }

    @GetMapping("/admin-add")
    public String adminAddView() {
        return "admin/admin-add";
    }

    @GetMapping("/role-edit/{roleId}")
    public String roleEditView(@PathVariable("roleId") Integer roleId, Model model) {
        Role role = roleService.getRoleByRoleId(roleId);
        List<Permission> permissionList = role.getPermissionList();
        List<Integer> flagList = new ArrayList<>();
        for (Permission permission : permissionList) {
            int permissionId = permission.getPermissionId();
            flagList.add(permissionId);
        }
        List<Permission> permissions = permissionService.getPermissionList();
        for (Permission permission : permissions) {
            int permissionId = permission.getPermissionId();
            if (flagList.contains(permissionId)) {
                permission.setFlag(true);
            }
        }
        model.addAttribute("permissions", permissions);
        model.addAttribute("role", role);
        return "admin/role-edit";
    }

    @PostMapping("/addTeacher")
    @ResponseBody
    public ResponseVo addTeacher(Teacher teacher, Authentication authentication) throws Exception {
        Teacher principal = (Teacher) authentication.getPrincipal();
        teacher.setTeacherSchoolId(principal.getTeacherSchoolId());
        Teacher save = teacherService.addTeacher(teacher);
        ResponseVo responseVo = new ResponseVo();
        if (save != null) {
            responseVo.setCode(200);
            responseVo.setData(save);
            responseVo.setMsg("添加成功！");
        } else {
            responseVo.setCode(403);
            responseVo.setMsg("添加失败！");
        }
        return responseVo;
    }

    @PutMapping("/updateTeacher")
    @ResponseBody
    public ResponseVo updataTeacher(Teacher teacher) {
        ResponseVo responseVo = new ResponseVo();
        Teacher update = teacherService.updateTeacher(teacher);
        if (update != null) {
            responseVo.setCode(200);
            responseVo.setData(update);
        } else {
            responseVo.setCode(403);
        }
        return responseVo;
    }

    @PostMapping("/uploadBathTeachers")
    @ResponseBody
    public ResponseVo saveBathTeachers(MultipartFile file, Authentication authentication) {
        Teacher principal = (Teacher) authentication.getPrincipal();
        ResponseVo responseVo = new ResponseVo();
        List<Teacher> teacherList = excelService.importTeacherData(file);
        if (teacherList != null) {
            teacherService.saveBathTeachers(teacherList, principal.getTeacherSchoolId());
            responseVo.setCode(200);
            responseVo.setMsg("添加成功！");
        } else {
            responseVo.setCode(403);
            responseVo.setMsg("添加失败！");
        }
        return responseVo;
    }
}
