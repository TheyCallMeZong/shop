package com.example.shop.controllers;

import com.example.shop.models.Product;
import com.example.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    private ProductService productService;

    @Autowired
    public MenuController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String catalog(Model model){
        model.addAttribute("products", productService.getProducts());
        return "catalog";
    }
}
