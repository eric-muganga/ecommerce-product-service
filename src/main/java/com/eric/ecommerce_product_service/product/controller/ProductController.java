package com.eric.ecommerce_product_service.product.controller;

import com.eric.ecommerce_product_service.product.dto.CreateProductDTO;
import com.eric.ecommerce_product_service.product.dto.ProductResponseDTO;
import com.eric.ecommerce_product_service.product.entity.Product;
import com.eric.ecommerce_product_service.product.service.IProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(@Qualifier("productServiceImpl") IProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> response = productService.getAllProducts().stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(modelMapper.map(product, ProductResponseDTO.class));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody CreateProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product saved = productService.addProduct(product);
        return ResponseEntity.ok(modelMapper.map(saved, ProductResponseDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody CreateProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setId(id);
        Product updated = productService.updateProduct(product);
        return ResponseEntity.ok(modelMapper.map(updated, ProductResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProducts(@RequestParam String name) {
        List<ProductResponseDTO> results = productService.searchProductsByName(name).stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable String name) {
        List<ProductResponseDTO> response = productService.getProductsByCategory(name).stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByPriceRange(@RequestParam double min, @RequestParam double max) {
        List<ProductResponseDTO> response = productService.getProductsByPriceRange(min, max).stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<ProductResponseDTO>> sortProducts(@RequestParam String sortBy, @RequestParam String direction) {
        List<ProductResponseDTO> response = productService.getProductsSorted(sortBy, direction).stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
