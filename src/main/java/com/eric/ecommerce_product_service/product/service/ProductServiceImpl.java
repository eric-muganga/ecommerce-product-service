package com.eric.ecommerce_product_service.product.service;

import com.eric.ecommerce_product_service.exceptions.ResourceNotFoundException;
import com.eric.ecommerce_product_service.product.entity.Product;
import com.eric.ecommerce_product_service.product.repo.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        Product productToUpdate = productRepository.findById(product.getId()).orElse(null);

        assert productToUpdate != null;
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setImage(product.getImage());
        productToUpdate.setCategory(product.getCategory());

        return productRepository.save(productToUpdate);
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        Product productToDelete = productRepository.findById(id).orElse(null);

        assert productToDelete != null;
        productRepository.delete(productToDelete);
    }

    @Override
    @Transactional
    public void updateStock(Long productId, int quantityPurchased) {
        Product productToUpdate = productRepository.findById(productId).orElse(null);
        assert productToUpdate != null;
        productToUpdate.setStock(productToUpdate.getStock() - quantityPurchased);
        productRepository.save(productToUpdate);
    }

    @Override
    public boolean isInStock(Long productId, int requestedQuantity) {
        Product product = getProductById(productId);
        return product.getStock() >= requestedQuantity;
    }

    @Override
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findAll().stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .toList();
    }


    @Override
    public List<Product> getProductsSorted(String sortBy, String direction) {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .sorted((a, b) -> {
                    int comparison = 0;
                    if ("price".equalsIgnoreCase(sortBy)) {
                        comparison = Double.compare(a.getPrice(), b.getPrice());
                    } else if ("name".equalsIgnoreCase(sortBy)) {
                        comparison = a.getName().compareToIgnoreCase(b.getName());
                    }
                    return "desc".equalsIgnoreCase(direction) ? -comparison : comparison;
                })
                .toList();
    }
}
