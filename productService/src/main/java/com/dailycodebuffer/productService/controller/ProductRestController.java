package com.dailycodebuffer.productService.controller;

import com.dailycodebuffer.productService.dto.ProductDTO;
import com.dailycodebuffer.productService.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        productDTO = productService.createProduct(productDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);

    }

    @PostMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOS = productService.getAllProducts();
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/upadte")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        productDTO = productService.updateProduct(productDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }


}
