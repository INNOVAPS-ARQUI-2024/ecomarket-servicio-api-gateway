package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF para APIs stateless
            .authorizeHttpRequests(authorize -> authorize
                // Permitir acceso público solo para solicitudes GET a estas rutas
                .requestMatchers("GET", "/api/productos/**").permitAll()
                .requestMatchers("GET", "/api/resenas/**").permitAll()
                .requestMatchers("GET", "/api/servicios/**").permitAll()
                .requestMatchers("GET", "/api/eventos/**").permitAll()

                // Todas las demás solicitudes requieren autenticación
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());  // Autenticación básica (puedes cambiar a JWT después)

        return http.build();
    }
}