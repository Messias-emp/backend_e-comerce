package com.ecommerce.auth;

import com.ecommerce.auth.dto.AuthResponse;

import java.util.HashMap;
import java.util.Map; // ✅ IMPORT QUE FALTAVA

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse login(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );

        // 🔐 UserDetails do Spring Security
        User principal = (User) authentication.getPrincipal();

        // 🎯 ROLE real extraída da authority
        String role = principal.getAuthorities()
            .iterator()
            .next()
            .getAuthority()
            .replace("ROLE_", "");

        // 🧾 CLAIMS DO JWT
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        // 🎫 TOKEN
        String token = jwtService.generateToken(
            claims,
            principal.getUsername() // email
        );

        return new AuthResponse(token, role);
    }
}