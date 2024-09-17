package com.example.ApiGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route to the user-service
                .route("user-service", r -> r.path("/users/**")
                        .uri("lb://USER-SERVICE"))

                // Route to the hotel-service
                .route("hotel-service", r -> r.path("/hotels/**")
                        .uri("lb://HOTEL-SERVICE"))

                // Route to the rating-service
                .route("rating-service", r -> r.path("/ratings/**")
                        .uri("lb://RATING-SERVICE"))
                .build();
    }
}
