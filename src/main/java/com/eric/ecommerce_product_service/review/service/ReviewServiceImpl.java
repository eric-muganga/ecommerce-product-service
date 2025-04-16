package com.eric.ecommerce_product_service.review.service;

import com.eric.ecommerce_product_service.review.entity.Review;
import com.eric.ecommerce_product_service.review.repo.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements IReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) {
        Review oldReview = getReviewById(review.getId());
        oldReview.setComment(review.getComment());
        oldReview.setRating(review.getRating());
        return reviewRepository.save(oldReview);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
