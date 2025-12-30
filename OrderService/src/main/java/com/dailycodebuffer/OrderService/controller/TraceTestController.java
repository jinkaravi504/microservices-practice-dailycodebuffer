package com.dailycodebuffer.OrderService.controller;

import brave.Span;
import brave.Tracer;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@Log4j2
@RestController
public class TraceTestController {

    private final Tracer tracer;

    public TraceTestController(Tracer tracer) {
        this.tracer = tracer;
    }

    @GetMapping("/trace-test")
    public Serializable test() {
        Span span = tracer.currentSpan();
        log.info("SPAN = {}", span);
        return span == null ? "NO TRACE" :span.context().traceId();
    }
}
