package com.ecommerce.ecommerce.dto;

import java.io.Serializable;
import java.util.List;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO implements Serializable {
    private Long id;
    private String name;
    List<PermissionDTO> Permissions;
    List<OperationDTO> operations;

    public RoleDTO(){
    }

    public RoleDTO(Long id, String name, List<PermissionDTO> permissions, List<OperationDTO> operations){
        this.id = id;
        this.name = name;
        Permissions = permissions;
        this.operations = operations;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<PermissionDTO> getPermissions(){
        return Permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions){
        Permissions = permissions;
    }

    public List<OperationDTO> getOperations(){
        return operations;
    }

    public void setOperations(List<OperationDTO> operations){
        this.operations = operations;
    }

}
