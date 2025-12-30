package com.dailycodebuffer.OrderService.interceptor;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignTracingInterceptor implements RequestInterceptor {

    private final Tracer tracer;

    public FeignTracingInterceptor(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void apply(RequestTemplate template) {

        Span span = tracer.currentSpan();

        if (span != null) {
            TraceContext ctx = span.context();

            String traceparent = String.format(
                    "00-%s-%s-01",
                    ctx.traceId(),
                    ctx.spanId()
            );

            template.header("traceparent", traceparent);
        }
    }


}
