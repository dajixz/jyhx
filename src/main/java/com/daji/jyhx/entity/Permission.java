package com.daji.jyhx.entity;

import com.daji.jyhx.repository.PaperRespository;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author 大稽
 * @date2018/10/1515:09
 */
@Entity
@Data
public class Permission {

    @Id
    @GeneratedValue
    private int permissionId;

    private String permissionName;

    private String url;

//    @ManyToMany
//    @JoinTable(name = "role_permission",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "permission_id")})
//    private List<Role> roles;

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
