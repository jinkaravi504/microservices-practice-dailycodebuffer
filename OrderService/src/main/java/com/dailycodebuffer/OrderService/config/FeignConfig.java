package com.dailycodebuffer.OrderService.config;

import com.dailycodebuffer.OrderService.external.decoder.FeignErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder getErrorDecode() {
        return new FeignErrorDecoder();
    }
}
