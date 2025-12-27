package com.dailycodebuffer.productService.service.impl;

import com.dailycodebuffer.productService.dto.ProductDTO;
import com.dailycodebuffer.productService.entity.Product;
import com.dailycodebuffer.productService.exception.ProductNotFoundException;
import com.dailycodebuffer.productService.repository.ProducrRepository;
import com.dailycodebuffer.productService.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProducrRepository producrRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        log.info("ProductServiceImpl : createProduct : START");
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        producrRepository.save(product);
        BeanUtils.copyProperties(product, productDTO);
        log.info("ProductServiceImpl : createProduct : END");
        return productDTO;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        log.info("ProductServiceImpl : getProductById : START");
        Product product = producrRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found", "PRODUVT_NOT_FOUNT"));
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        log.info("ProductServiceImpl : getProductById : END");
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("ProductServiceImpl : getAllProducts : START");
        List<ProductDTO> products = producrRepository.findAll().stream().map(product -> {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            return productDTO;
        }).collect(Collectors.toList());
        log.info("ProductServiceImpl : getAllProducts : END");
        return products;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        log.info("ProductServiceImpl : updateProduct : START");
        Product product = producrRepository.findById(productDTO.getId()).orElseThrow(() -> new ProductNotFoundException("Product not found", "PRODUVT_NOT_FOUNT"));
        BeanUtils.copyProperties(productDTO, product);
        producrRepository.save(product);
        BeanUtils.copyProperties(product, productDTO);
        log.info("ProductServiceImpl : updateProduct : END");
        return productDTO;
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("ProductServiceImpl : deleteProduct : START");
        producrRepository.deleteById(id);
        log.info("ProductServiceImpl : deleteProduct : END");
    }
}
