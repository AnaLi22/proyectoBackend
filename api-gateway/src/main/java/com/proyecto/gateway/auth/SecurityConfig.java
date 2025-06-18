package com.proyecto.gateway.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig  {

	@Bean
	SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity http) {
		http
		.csrf(ServerHttpSecurity.CsrfSpec::disable)
		.authorizeExchange(exchange -> exchange
				.pathMatchers("/eureka/**", "/actuator/**").permitAll()
				.pathMatchers(HttpMethod.GET,"/**").permitAll()
				.pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyExchange().authenticated()
			)
		.httpBasic(Customizer.withDefaults());
		return http.build();	
	}
	
}
