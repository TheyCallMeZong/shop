package com.example.shop.service;

import com.example.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsByNameAndColor(String name, String color){
        return productRepository.findByNameAndColor(name, color);
    }

    public List<Product> getProducts(){
        List<Product> counts = new ArrayList<>();
        productRepository.findAll().forEach(counts::add);
        return counts;
    }
}
