package com.learnwithmohit.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="role")
public class Role {

    @Id
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;
}
