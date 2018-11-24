package com.daji.jyhx.entity;


import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * @author 大稽
 * @date2018/10/1514:45
 */
@Entity
@Data
public class Role implements Serializable{

    private static final long serialVersionUID = 5953489686174757750L;
    @Id
    @GeneratedValue
    private int roleId;

    private String roleName;

    private String roleDescription;
//    @ManyToMany
//    @JoinTable(name = "teacher_role",joinColumns = {@JoinColumn(name = "teacher_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    private List<Teacher> teachers;

    @Transient
    private List<Integer> permissions;

    @Transient
    private boolean flag;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "role_permission",joinColumns = {@JoinColumn(name = "permission_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Permission> permissionList;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }
}
