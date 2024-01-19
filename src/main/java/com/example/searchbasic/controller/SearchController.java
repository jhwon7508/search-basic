package com.example.searchbasic.controller;

import com.example.searchbasic.etc.ProductRequestDto;
import com.example.searchbasic.etc.ProductResponseDTO;
import com.example.searchbasic.etc.ResponseCode;
import com.example.searchbasic.etc.SearchKeywordDto;
import com.example.searchbasic.service.ProductService;
import com.example.searchbasic.service.SearchService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SearchController {
    private final SearchService searchService;
    private final ProductService productService;

    public SearchController(SearchService searchService, ProductService productService) {
        this.searchService = searchService;
        this.productService = productService;
    }

    @PostMapping("/product/addMultiple")
    public ResponseEntity<String> addProducts(@RequestBody List<ProductRequestDto> productList) {
        productService.addProducts(productList);
        return ResponseEntity.ok("Products added successfully");
    }

    @PostMapping("/product/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequestDto product) {
        productService.addProduct(product);
        return ResponseEntity.ok("Product added successfully");
    }

    @GetMapping("/product/detail/{productId}")
    public ResponseEntity<?> getProductById(@Valid @PathVariable Long productIdx) {
        try {
            ProductResponseDTO product = productService.getProductById(productIdx);
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseCode.INVALID_PRODUCT);
        }
    }
    @GetMapping("/product/all")
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(Pageable pageable) {
        Page<ProductResponseDTO> productPage = productService.getAllProducts(pageable);
        return ResponseEntity.ok(productPage);
    }

    @PostMapping("/search")
    public SearchKeywordDto search(String keyword) {
        return searchService.save(keyword);
    }

}