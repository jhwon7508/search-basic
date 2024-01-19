package com.example.searchbasic.service;

import com.example.searchbasic.etc.Product;
import com.example.searchbasic.etc.ProductRequestDto;
import com.example.searchbasic.etc.ProductResponseDTO;
import com.example.searchbasic.etc.ResponseCode;
import com.example.searchbasic.respository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void addProducts(List<ProductRequestDto> productList) {
        List<Product> products = productList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        productRepository.saveAll(products);
    }

    public void addProduct(ProductRequestDto productDto) {
        Product product = convertToEntity(productDto);
        productRepository.save(product);
    }

    public ProductResponseDTO getProductById(Long productIdx) {
        try {
            Product product = productRepository.getById(productIdx);
            ProductResponseDTO result = ProductResponseDTO.builder()
                    .productIdx(productIdx)
                    .productCode(product.getProductCode())
                    .productName(product.getProductName())
                    .price(product.getPrice())
                    .stockQuantity(product.getStockQuantity())
                    .isSoldOut(product.getIsSoldOut())
                    .build();
            Boolean isSoldOut = result.getStockQuantity() <= 0;
            result.setIsSoldOut(isSoldOut);
            return result;
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.DATABASE_ERROR.toString());
        }
    }

    @Cacheable(value = "products", key = "#pageable")
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(product -> new ProductResponseDTO(
                product.getIdx(),
                product.getProductCode(),
                product.getProductName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getStockQuantity() <= 0));
    }

    private Product convertToEntity(ProductRequestDto productDto) {
        Product product = new Product();
        product.setProductCode(productDto.getProductCode());
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        return product;
    }

}
