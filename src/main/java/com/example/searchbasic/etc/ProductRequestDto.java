package com.example.searchbasic.etc;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductRequestDto {

    @NotNull
    private String productCode;

    @NotNull
    private String productName;

    @NotNull
    private Long price;

    @NotNull
    private Integer stockQuantity;

    @NotNull
    private String category;

    private String description;

}