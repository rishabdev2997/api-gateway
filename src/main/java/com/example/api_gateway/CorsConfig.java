package com.example.api_gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Value("${allowed.origin}")
    private String allowedOrigin;

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // Allow the exact frontend origin configured in env variables
        config.setAllowedOrigins(Arrays.asList(allowedOrigin));

        // Allow all headers (you might want to restrict this if needed)
        config.addAllowedHeader(CorsConfiguration.ALL);

        // Allow typical HTTP methods including OPTIONS explicitly
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Allow credentials (cookies/auth headers)
        config.setAllowCredentials(true);

        // Optionally set max age to cache CORS preflight responses for performance
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Apply this configuration to all routes
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}