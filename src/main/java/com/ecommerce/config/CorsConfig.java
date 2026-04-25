/*package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        // Frontend Angular
        config.setAllowedOrigins(List.of("http://localhost:4200"));

        // Permitir envio de Authorization header
        config.setAllowCredentials(true);

        // Headers liberados
        config.setAllowedHeaders(List.of("*"));

        // Métodos liberados
        config.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        // Expor header Authorization na resposta (boa prática)
        config.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
*/
package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        // 🎯 Configuração central do CORS
        CorsConfiguration config = new CorsConfiguration();

        // =====================================================
        // 🌍 ORIGENS PERMITIDAS
        // =====================================================
        // ✔ DEV (Angular local)
        // ✔ PROD (seu frontend deployado)
        config.setAllowedOrigins(List.of(

        "http://localhost:4200",
        "https://*.vercel.app"
        ));// 🔥 trocar depois
        

        // =====================================================
        // 🔐 CREDENCIAIS (JWT / cookies)
        // =====================================================
        // Necessário para enviar Authorization header
        config.setAllowCredentials(true);

        // =====================================================
        // 📡 HEADERS PERMITIDOS
        // =====================================================
        config.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "Accept"
        ));

        // =====================================================
        // 🔓 MÉTODOS HTTP LIBERADOS
        // =====================================================
        config.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        // =====================================================
        // 📤 HEADERS EXPOSTOS (IMPORTANTE PARA JWT)
        // =====================================================
        config.setExposedHeaders(List.of(
                "Authorization"
        ));

        // =====================================================
        // ⚡ CACHE DO CORS (melhora performance)
        // =====================================================
        config.setMaxAge(3600L); // 1 hora

        // =====================================================
        // 🔗 REGISTRO GLOBAL
        // =====================================================
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}