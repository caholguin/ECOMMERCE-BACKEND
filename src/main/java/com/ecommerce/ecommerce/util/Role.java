package com.ecommerce.ecommerce.util;

import java.util.Arrays;
import java.util.List;

public enum Role {

    ROLE_ADMINISTRATOR(Arrays.asList(
            RolePermission.READ_FAMILIES,
            RolePermission.READ_FAMILY,
            RolePermission.CREATE_FAMILY,
            RolePermission.UPDATE_FAMILY,
            RolePermission.DELETE_FAMILY,

            RolePermission.READ_CATEGORIES,
            RolePermission.READ_CATEGORY,
            RolePermission.CREATE_CATEGORY,
            RolePermission.UPDATE_CATEGORY,
            RolePermission.DELETE_CATEGORY
    )),

    ROLE_ASISTANT_ADMINISTRATOR(Arrays.asList(
            RolePermission.READ_FAMILIES,
            RolePermission.READ_FAMILY,
            RolePermission.CREATE_FAMILY,
            RolePermission.UPDATE_FAMILY,


            RolePermission.READ_CATEGORIES,
            RolePermission.READ_CATEGORY,
            RolePermission.CREATE_CATEGORY,
            RolePermission.UPDATE_CATEGORY

    )),

    ROLE_CUSTOMER(Arrays.asList(
            RolePermission.READ_FAMILIES,
            RolePermission.READ_CATEGORIES,
            RolePermission.READ_MY_PROFILE
    ));

    private List<RolePermission> permissions;

    Role(List<RolePermission> permissions){
        this.permissions = permissions;
    }

    public List<RolePermission> getPermissions(){
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions){
        this.permissions = permissions;
    }
}
