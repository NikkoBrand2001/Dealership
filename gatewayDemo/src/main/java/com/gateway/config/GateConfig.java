package com.gateway.config;

import com.gateway.filter.GateFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GateConfig {

    private final GateFilter gateFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("client-service", r -> r.path("/client/**")
                        .filters(f -> f.filter(gateFilter))
                        .uri("http://localhost:8082"))

                .route("car-service", r -> r.path("/car/**")
                        .filters(f -> f.filter(gateFilter))
                        .uri("http://localhost:8081"))

                .route("security-service", r -> r.path("/auth/**")
                        .uri("http://localhost:9898"))

                .route("admin-service", r -> r.path("/admin/**")
                        .filters(f -> f.filter(gateFilter))
                        .uri("http://localhost:9898"))
                .build();
    }

}
