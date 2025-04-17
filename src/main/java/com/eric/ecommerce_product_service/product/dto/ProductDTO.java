package com.eric.ecommerce_product_service.product.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int stock;
    private String image;
    private Long categoryId;
}
