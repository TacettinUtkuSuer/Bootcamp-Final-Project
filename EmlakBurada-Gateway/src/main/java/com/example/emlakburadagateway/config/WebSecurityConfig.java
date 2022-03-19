package com.example.emlakburadagateway.config;

import com.example.emlakburadagateway.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class WebSecurityConfig{

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //// @formatter:off
        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable();

        return http.build();
        // @formatter:on

    }

    @Autowired
    JwtFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                //// @formatter:off


                .route("auth",
                        r -> r.path("/auth/**")
                                .uri("http://localhost:9094"))
                .route("emlakburada",
                        r -> r.method(HttpMethod.POST)
                                .and()
                                .path(("/adverts/**"))
                                .filters(f -> f.filter(filter)).uri("http://localhost:9091"))
                .route("emlakburada",
                        r -> r.method(HttpMethod.GET)
                                .and()
                                .path("/hello/**")
                                .filters(f -> f.filter(filter)).uri("http://localhost:9091"))
                .route("emlakburada",
                        r -> r.method(HttpMethod.GET)
                                .and()
                                .path("/gettoken/**")
                                .filters(f -> f.filter(filter)).uri("http://localhost:9091"))
                .route("emlakburada-banner",
                        r -> r.path("/banners/**")
                                .filters(f -> f.filter(filter)).uri("http://localhost:8081"))
                .build();

        // @formatter:on
    }

}
