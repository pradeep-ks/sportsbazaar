package com.sportsbazaar.persistence.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sportsbazaar.persistence.enums.RoleName;

@Entity
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
    
}
