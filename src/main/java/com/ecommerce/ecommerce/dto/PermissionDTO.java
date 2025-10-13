package com.ecommerce.ecommerce.dto;

import java.io.Serializable;
import java.util.List;

public class PermissionDTO implements Serializable {

    private Long id;
    private Long roleId;
    private Long operationId;
    private List<OperationDTO> operations;

    public PermissionDTO(){
    }

    public PermissionDTO(Long id, Long roleId, Long operationId, List<OperationDTO> operations){
        this.id = id;
        this.roleId = roleId;
        this.operationId = operationId;
        this.operations = operations;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getRoleId(){
        return roleId;
    }

    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }

    public Long getOperationId(){
        return operationId;
    }

    public void setOperationId(Long operationId){
        this.operationId = operationId;
    }

    public List<OperationDTO> getOperations(){
        return operations;
    }

    public void setOperations(List<OperationDTO> operations){
        this.operations = operations;
    }
}
