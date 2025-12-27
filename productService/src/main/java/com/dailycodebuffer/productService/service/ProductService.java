package com.dailycodebuffer.productService.service;

import com.dailycodebuffer.productService.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public ProductDTO createProduct(ProductDTO productDTO);

    public ProductDTO getProductById(Long id);

    public List<ProductDTO> getAllProducts();

    public ProductDTO updateProduct(ProductDTO productDTO);

    public void deleteProduct(Long id);
}
