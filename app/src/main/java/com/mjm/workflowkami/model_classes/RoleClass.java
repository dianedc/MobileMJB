package com.mjm.workflowkami.model_classes;

import java.io.Serializable;

/**
 * Created by DC on 02/02/2018.
 */

public class RoleClass implements Serializable {

    private Integer roleID;
    private String role;

    public RoleClass() {
    }

    public RoleClass(Integer roleID, String role) {
        this.roleID = roleID;
        this.role = role;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
