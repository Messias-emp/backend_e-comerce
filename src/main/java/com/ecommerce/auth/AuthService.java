package com.ecommerce.auth;

import com.ecommerce.auth.dto.AuthResponse;

import java.util.HashMap;
import java.util.Map; // ✅ IMPORT QUE FALTAVA

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.auth.dto.RegisterRequest;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
        AuthenticationManager authenticationManager,
        JwtService jwtService,
        UserRepository userRepository,
        PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;        
    }

    public AuthResponse login(String email, String password) {

      Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(email, password)
);

org.springframework.security.core.userdetails.User principal =
    (org.springframework.security.core.userdetails.User)
    authentication.getPrincipal();

        // 🔐 UserDetails do Spring Security
       
      
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

    public void register(RegisterRequest request) {

    // verifica se email já existe
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        throw new RuntimeException("Email já cadastrado");
    }

    // cria usuário
    com.ecommerce.auth.User user = new com.ecommerce.auth.User();
    

    user.setName(request.getName());
    user.setEmail(request.getEmail());

    // senha criptografada
    user.setPassword(
        passwordEncoder.encode(request.getPassword())
    );

    // role padrão
    user.setRole(Role.USER);

    // salva
    userRepository.save(user);
}
   
}