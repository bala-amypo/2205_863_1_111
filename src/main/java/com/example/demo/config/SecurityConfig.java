package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

http
.csrf(csrf -> csrf.disable())
.formLogin(form -> form.disable())
.httpBasic(basic -> basic.disable())
.authorizeHttpRequests(auth -> auth
.requestMatchers(
"/auth/**",
"/swagger-ui/**",
"/v3/api-docs/**"
).permitAll()
.anyRequest().permitAll()   
);

return http.build();
}
}