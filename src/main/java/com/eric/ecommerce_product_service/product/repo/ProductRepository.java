package com.eric.ecommerce_product_service.product.repo;

import com.eric.ecommerce_product_service.category.entity.Category;
import com.eric.ecommerce_product_service.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);

    List<Product> findByPrice(double price);

    List<Product> findByCategory(Category category);

    List<Product> findByCategoryName(String categoryName);
}
