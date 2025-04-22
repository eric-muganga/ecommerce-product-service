package com.eric.ecommerce_product_service.review.controller;

import com.eric.ecommerce_product_service.review.entity.Review;
import com.eric.ecommerce_product_service.review.service.IReviewService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private  final IReviewService reviewService;

    public ReviewController(@Qualifier("reviewServiceImpl") IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable("id") Long id, @RequestBody Review review) {
        review.setId(id);
        return ResponseEntity.ok(reviewService.updateReview(review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
