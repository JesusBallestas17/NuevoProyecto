package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // Definir un usuario en memoria con rol ADMIN
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN") // Se guarda como "ROLE_ADMIN" internamente
                .build();

        return new InMemoryUserDetailsManager(user);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Deshabilita CSRF solo para pruebas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // Acceso libre a "/public/**"
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Protegido para ADMIN
                        .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
                )
                .formLogin() // Habilita el formulario de login de Spring Security
                .and()
                .httpBasic(); // Autenticación básica

        return http.build();
    }
}
