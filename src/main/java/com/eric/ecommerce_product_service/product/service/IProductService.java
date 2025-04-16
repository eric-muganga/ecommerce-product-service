package com.eric.ecommerce_product_service.product.service;

import com.eric.ecommerce_product_service.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    // Get all products
    List<Product> getAllProducts();

    // Get a product by its ID
    Product getProductById(Long id);

    // Get products by category
    List<Product> getProductsByCategory(String categoryName);

    // Get products by name (search)
    List<Product> searchProductsByName(String name);


    // admin controls
    Product addProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(long id);

    // Update stock after purchase
    void updateStock(Long productId, int quantityPurchased);

    // Check if product is in stock
    boolean isInStock(Long productId, int requestedQuantity);

    // Get products within a price range
    List<Product> getProductsByPriceRange(double minPrice, double maxPrice);

    // Get products sorted by price or name (asc/desc)
    List<Product> getProductsSorted(String sortBy, String direction);

}
