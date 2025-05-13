package com.isofttz.family.gateway.family_gateway.errors;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@Component
public class ErrorLoggingFilter implements GatewayFilter {

    private static final Logger logger = LoggerFactory.getLogger(ErrorLoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange)
                .doOnError(error -> logger.error("Gateway error: ", error))  // Log error here
                .doOnTerminate(() -> logger.info("Request finished: " + exchange.getRequest().getURI()));  // Optional: Log completion
    }
}

