package com.eric.ecommerce_product_service.review.service;

import com.eric.ecommerce_product_service.review.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IReviewService {
    List<Review> getAllReviews();
    Review getReviewById(Long id);
    Review addReview(Review review);
    Review updateReview(Review review);
    void deleteReview(Long id);
}
