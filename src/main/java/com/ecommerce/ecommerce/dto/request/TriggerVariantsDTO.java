package com.ecommerce.ecommerce.dto.request;

import java.util.List;

public class TriggerVariantsDTO {

    private List<List<Long>> arrays;

    public List<List<Long>> getArrays(){
        return arrays;
    }

    public void setArrays(List<List<Long>> arrays){
        this.arrays = arrays;
    }
}
