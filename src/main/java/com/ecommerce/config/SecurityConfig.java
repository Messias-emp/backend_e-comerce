package com.ecommerce.config;

import com.ecommerce.auth.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    // 🔐 Password encoder (BCrypt padrão mercado)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔑 AuthenticationProvider baseado no UserDetailsService
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    // 🔒 Cadeia principal de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {}) // Usa CorsConfigurationSource
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/api/products/**").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/orders/**").authenticated()
                    .requestMatchers(HttpMethod.POST, "/orders/**").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/orders/**").hasRole("USER")


                   
                    .anyRequest().authenticated()
            )
            
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter,
                    UsernamePasswordAuthenticationFilter.class)
            .headers(headers ->
                    headers.frameOptions(frame -> frame.disable())
                    
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Configuration // 🔥 Diz pro Spring que é config global
    public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**") // 🌍 libera todas rotas

                        // 🔗 URL do seu frontend (produção)
                        .allowedOrigins(
                                "http://localhost:4200", // dev
                                "https://seu-frontend.vercel.app" // prod
                        )

                        // 🔓 métodos liberados
                        .allowedMethods("GET", "POST", "PUT", "DELETE")

                        // 🔑 headers (JWT precisa disso)
                        .allowedHeaders("*")

                        // 🔐 importante pra auth
                        .allowCredentials(true);
            }
        };
    }
}
}
