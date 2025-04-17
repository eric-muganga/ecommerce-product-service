package com.eric.ecommerce_product_service.config;

import com.eric.ecommerce_product_service.category.entity.Category;
import com.eric.ecommerce_product_service.category.repo.CategoryRepository;
import com.eric.ecommerce_product_service.product.dto.CreateProductDTO;
import com.eric.ecommerce_product_service.product.dto.ProductResponseDTO;
import com.eric.ecommerce_product_service.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
    private final CategoryRepository categoryRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Custom mapping: Product -> ProductResponseDTO
        mapper.addMappings(new PropertyMap<Product, ProductResponseDTO>() {
            @Override
            protected void configure() {
                map().setCategoryName(source.getCategory().getName());
            }
        });


        // Custom mapping from CreateProductDTO -> Product
        mapper.addMappings(new PropertyMap<CreateProductDTO, Product>() {
            @Override
            protected void configure() {
                // Skip category by default, we’ll handle it manually
                skip(destination.getCategory());
            }
        });

        // Post converter for category
        mapper.typeMap(CreateProductDTO.class, Product.class).setPostConverter(context -> {
            CreateProductDTO source = context.getSource();
            Product destination = context.getDestination();
            Category category = categoryRepository.findById(source.getCategoryId()).orElse(null);
            destination.setCategory(category);
            return destination;
        });

        return mapper;
    }
}
