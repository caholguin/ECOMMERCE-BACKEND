package com.ecommerce.ecommerce.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubcategoryDTO {

    private Long id;

    private String name;

    private Long categoryId;
}
