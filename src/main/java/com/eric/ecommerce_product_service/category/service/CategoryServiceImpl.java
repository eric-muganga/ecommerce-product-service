package com.eric.ecommerce_product_service.category.service;

import com.eric.ecommerce_product_service.category.entity.Category;
import com.eric.ecommerce_product_service.category.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category oldCategory = getCategoryById(category.getId());
        oldCategory.setName(category.getName());
        oldCategory.setDescription(category.getDescription());

        return categoryRepository.save(oldCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
