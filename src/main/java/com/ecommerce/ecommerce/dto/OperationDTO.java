package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.dto.response.ModuleDTO;

import java.io.Serializable;

public class OperationDTO implements Serializable {

    private Long id;

    private String name;

    private String path;

    private String httpMethod;

    private boolean permitAll;

    private ModuleDTO module;

    public OperationDTO(){
    }

    public OperationDTO(Long id, String name, String path, String httpMethod, boolean permitAll, ModuleDTO module){
        this.id = id;
        this.name = name;
        this.path = path;
        this.httpMethod = httpMethod;
        this.permitAll = permitAll;
        this.module = module;
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

    public String getPath(){
        return path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public String getHttpMethod(){
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod){
        this.httpMethod = httpMethod;
    }

    public boolean isPermitAll(){
        return permitAll;
    }

    public void setPermitAll(boolean permitAll){
        this.permitAll = permitAll;
    }

    public ModuleDTO getModule(){
        return module;
    }

    public void setModule(ModuleDTO module){
        this.module = module;
    }
}
