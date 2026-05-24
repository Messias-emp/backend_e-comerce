package com.ecommerce.config;

import com.ecommerce.auth.JwtAuthFilter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    // 🔐 Encoder de senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔑 Provider de autenticação
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // 🌍 CORS CENTRALIZADO (ÚNICO)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        // 🔥 Libera localhost + qualquer deploy da Vercel
 config.setAllowedOriginPatterns(List.of(
            "http://localhost:4200",
            "https://frontend-angular-g2ev.vercel.app"
    ));

        // 🔐 Permite JWT
        config.setAllowCredentials(true);

        // 📡 Headers liberados
        config.setAllowedHeaders(List.of("*"));

        // 🔓 Métodos
        config.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        // 📤 Expor Authorization
        config.setExposedHeaders(List.of("Authorization"));

        // ⚡ Cache
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    // 🔒 Segurança principal
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {}) // usa config acima
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                    // 🔥 ESSENCIAL: liberar preflight
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                    // públicas
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/products/**").permitAll()

                    // protegidas
                    .requestMatchers("/orders/**").authenticated()
                    .requestMatchers(HttpMethod.POST, "/orders/**").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/orders/**").hasRole("USER")

                    .anyRequest().authenticated()
            )

            .authenticationProvider(authenticationProvider())

            .addFilterBefore(jwtAuthFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 🔑 Auth manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}