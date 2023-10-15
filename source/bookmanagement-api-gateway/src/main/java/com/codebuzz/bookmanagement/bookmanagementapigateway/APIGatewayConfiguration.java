package com.codebuzz.bookmanagement.bookmanagementapigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfiguration {
    
    public RouteLocator getRoute(RouteLocatorBuilder builder){
        return builder.routes().route(p->p.path("**/api/**").uri("lb://task")).build();
    }
}
