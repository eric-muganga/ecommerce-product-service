package com.eric.ecommerce_product_service.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDTO {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Double price;

    @Min(0)
    private int stock;

    private String image;

    @NotNull
    private Long categoryId;
}
