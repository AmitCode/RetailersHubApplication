package com.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@SpringBootApplication
public class ApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServerApplication.class, args);
	}

    @Bean
    public RouteLocator retailersHubAppRoutingConfig(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/auth-application/authService/**")
                        .filters(f -> f.rewritePath("/auth-application/?(?<remaining>.*)", "" +
                                "/${remaining}")
                                .addRequestHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("authApplicationCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://AUTH-APPLICATION"))
                .route(p -> p
                        .path("/userservice-application/userService/**")
                        .filters(f -> f.rewritePath("/userservice-application/?(?<remaining>.*)", "/${remaining}")
                                .addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://NOTIFICATION-APPLICATION"))
                .route(p -> p
                        .path("/notification-application/**")
                        .filters(f -> f.rewritePath("/notification-application/?(?<remaining>.*)", "/${remaining}")
                                .addRequestHeader("X-Response-Time", LocalDateTime.now().toString())
                                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver())))
                        .uri("lb:auth-application"))
                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter(){
        return new RedisRateLimiter(1,1,1);
    }

    @Bean
    KeyResolver userKeyResolver(){
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders()
                .getFirst("user")).defaultIfEmpty("anonymous");

    }
}
