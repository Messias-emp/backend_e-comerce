package com.ecommerce.sercurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * Retorna o email do usuário autenticado
     */
    public static String getAuthenticatedEmail() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return authentication.getName(); // 🔥 Aqui está o email
    }

    /**
     * Se você não estiver usando ID no JWT,
     * pode remover totalmente esse método.
     */
    public static Long getAuthenticatedUserId() {
        return null; // simplifique enquanto não usa ID no token
    }
}