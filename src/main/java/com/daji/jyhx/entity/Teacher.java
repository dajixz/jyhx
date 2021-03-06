package com.daji.jyhx.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 大稽
 * @date2018/8/915:45
 */
//教师表
@Entity
@Data
public class Teacher implements UserDetails,Serializable{

    private static final long serialVersionUID = -4161506096071021721L;
    //教师id
    @Id
    private String teacherId;

    //教师姓名
    private String teacherName;

    //教师手机号
    private String teacherTel;

    //教师密码
    private String teacherPassword;

    //教师邮箱
    private String teacherEmail;

    private String teacherGradeId;

    private String teacherSchoolId;

    private String teacherResources;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "teacher_role",joinColumns = {@JoinColumn(name = "teacher_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList ;



    @Transient
    private int role;

    @Transient
    private int[] roles;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherTel='" + teacherTel + '\'' +
                ", teacherPassword='" + teacherPassword + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", teacherGradeId='" + teacherGradeId + '\'' +
                ", teacherSchoolId='" + teacherSchoolId + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.teacherPassword;
    }

    @Override
    public String getUsername() {
        return this.teacherId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
