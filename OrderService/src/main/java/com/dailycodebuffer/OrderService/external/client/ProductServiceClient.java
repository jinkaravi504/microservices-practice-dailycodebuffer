package com.dailycodebuffer.OrderService.external.client;

import com.dailycodebuffer.OrderService.entity.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service/api/products")
public interface ProductServiceClient {

    @PutMapping("/reduce-quantity")
    public ResponseEntity<Void> reduceQuanitity(@RequestBody ProductDTO productDTO);
}
