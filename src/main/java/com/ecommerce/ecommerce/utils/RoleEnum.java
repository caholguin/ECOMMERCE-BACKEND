package com.ecommerce.ecommerce.utils;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {

    ROLE_ADMINISTRATOR(Arrays.asList(
            RolePermissionEnum.READ_FAMILIES,
            RolePermissionEnum.READ_FAMILY,
            RolePermissionEnum.CREATE_FAMILY,
            RolePermissionEnum.UPDATE_FAMILY,
            RolePermissionEnum.DELETE_FAMILY,

            RolePermissionEnum.READ_CATEGORIES,
            RolePermissionEnum.READ_CATEGORY,
            RolePermissionEnum.CREATE_CATEGORY,
            RolePermissionEnum.UPDATE_CATEGORY,
            RolePermissionEnum.DELETE_CATEGORY
    )),

    ROLE_ASISTANT_ADMINISTRATOR(Arrays.asList(
            RolePermissionEnum.READ_FAMILIES,
            RolePermissionEnum.READ_FAMILY,
            RolePermissionEnum.CREATE_FAMILY,
            RolePermissionEnum.UPDATE_FAMILY,


            RolePermissionEnum.READ_CATEGORIES,
            RolePermissionEnum.READ_CATEGORY,
            RolePermissionEnum.CREATE_CATEGORY,
            RolePermissionEnum.UPDATE_CATEGORY

    )),

    ROLE_CUSTOMER(Arrays.asList(
            RolePermissionEnum.READ_FAMILIES,
            RolePermissionEnum.READ_CATEGORIES,
            RolePermissionEnum.READ_MY_PROFILE
    ));

    private List<RolePermissionEnum> permissions;

    RoleEnum(List<RolePermissionEnum> permissions){
        this.permissions = permissions;
    }

    public List<RolePermissionEnum> getPermissions(){
        return permissions;
    }

    public void setPermissions(List<RolePermissionEnum> permissions){
        this.permissions = permissions;
    }
}
