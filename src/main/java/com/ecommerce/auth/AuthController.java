package com.ecommerce.auth;

import com.ecommerce.auth.dto.AuthResponse;
import com.ecommerce.auth.dto.LoginRequest;
import com.ecommerce.common.dto.ApiResponse;

import com.ecommerce.auth.dto.RegisterRequest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200") // 👈 LINHA NOVA
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

      // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @RequestBody LoginRequest request) {

        AuthResponse response =
                authService.login(request.getEmail(), request.getPassword());

        ApiResponse<AuthResponse> apiResponse =
                new ApiResponse<>(true, "Login realizado com sucesso", response);

        return ResponseEntity.ok(apiResponse);
    }
    // REGISTER
@PostMapping("/register")
public ResponseEntity<?> register(
        @RequestBody RegisterRequest request
) {

    authService.register(request);

    return ResponseEntity.ok(
        Map.of(
            "success", true,
            "message", "Usuário registrado com sucesso"
        )
    );
}
}