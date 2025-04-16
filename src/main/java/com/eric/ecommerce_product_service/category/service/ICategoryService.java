package com.eric.ecommerce_product_service.category.service;

import com.eric.ecommerce_product_service.category.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}
