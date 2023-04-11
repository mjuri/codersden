package uk.codersden.apigatewayserver;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("profiles-service", r -> r.path("/profiles/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://profiles-service"))
                .build();
    }
}