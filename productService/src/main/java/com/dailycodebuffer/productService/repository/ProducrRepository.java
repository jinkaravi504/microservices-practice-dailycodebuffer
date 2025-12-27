package com.dailycodebuffer.productService.repository;

import com.dailycodebuffer.productService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducrRepository extends JpaRepository<Product, Long> {
}
