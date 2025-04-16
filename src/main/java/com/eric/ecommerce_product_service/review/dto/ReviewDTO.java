package com.eric.ecommerce_product_service.review.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private Long productId;
    private Long userId;
    private String comment;
    private int rating;
}
