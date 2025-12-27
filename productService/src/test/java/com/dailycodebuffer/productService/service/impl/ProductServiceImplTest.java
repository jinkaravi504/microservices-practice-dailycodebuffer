package com.dailycodebuffer.productService.service.impl;

import com.dailycodebuffer.productService.dto.ProductDTO;
import com.dailycodebuffer.productService.entity.Product;
import com.dailycodebuffer.productService.repository.ProducrRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceImplTest {

    @Mock
    private ProducrRepository producrRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @Order(1)
    void createProduct_success() {
        ProductDTO dto = new ProductDTO();
        dto.setProductName("Laptop");
        dto.setPrice(new BigDecimal(50000));

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setProductName("Laptop");
        savedProduct.setPrice(new BigDecimal(50000));

        when(producrRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductDTO result = productService.createProduct(dto);

        assertNotNull(result);
        assertEquals("Laptop", result.getProductName());
        assertEquals(new BigDecimal(50000.0), result.getPrice());
        verify(producrRepository, times(1)).save(any(Product.class));
    }

    @Test
    @Order(2)
    void getProductById_success() {
        Product product = new Product();
        product.setId(1L);
        product.setProductName("Mobile");
        product.setPrice(new BigDecimal(20000));

        when(producrRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO dto = productService.getProductById(1L);

        assertNotNull(dto);
        assertEquals("Mobile", dto.getProductName());
        assertEquals(new BigDecimal(20000.0), dto.getPrice());
        verify(producrRepository, times(1)).findById(any(Long.class));
    }

    @Test
    @Order(3)
    void getProductById_notFound() {
        when(producrRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> productService.getProductById(1L)
        );

        assertEquals("Product not found", ex.getMessage());
    }

    @Test
    @Order(4)
    void getAllProducts_success() {
        Product p1 = new Product(1L, "TV", new BigDecimal(30000), 10L);
        Product p2 = new Product(2L, "Fridge", new BigDecimal(40000.0), 10L);

        when(producrRepository.findAll()).thenReturn(List.of(p1, p2));

        List<ProductDTO> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("TV", result.get(0).getProductName());
        assertEquals("Fridge", result.get(1).getProductName());
        verify(producrRepository, times(1)).findAll();
    }

    @Test
    @Order(5)
    void updateProduct_success() {
        Product existing = new Product();
        existing.setId(1L);
        existing.setProductName("Old Name");
        existing.setPrice(new BigDecimal(1000.0));

        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setProductName("New Name");
        dto.setPrice(new BigDecimal(2000.0));

        when(producrRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(producrRepository.save(any(Product.class))).thenReturn(existing);

        ProductDTO result = productService.updateProduct(dto);

        assertEquals("New Name", result.getProductName());
        assertEquals(new BigDecimal(2000.0), result.getPrice());
        verify(producrRepository).save(existing);
    }

    @Test
    @Order(6)
    void deleteProduct_success() {
        doNothing().when(producrRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(producrRepository, times(1)).deleteById(1L);
    }
}
