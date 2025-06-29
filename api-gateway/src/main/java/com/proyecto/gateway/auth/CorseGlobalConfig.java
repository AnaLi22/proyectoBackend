package com.proyecto.gateway.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
public class CorseGlobalConfig {

	@Bean
	CorsWebFilter corsWebFilter() {
		 CorsConfiguration config = new CorsConfiguration();
		 config.addAllowedOrigin("http://localhost:4200");
		 config.addAllowedHeader("*");
		 config.addAllowedMethod("*");
		 config.setAllowCredentials(true);
		 
		 UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		 source.registerCorsConfiguration("/**", config);
		 
		 return new CorsWebFilter(source);
	}
}
