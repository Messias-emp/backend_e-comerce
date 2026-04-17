package com.ecommerce.config;

import com.ecommerce.auth.User;
import com.ecommerce.auth.Role;
import com.ecommerce.auth.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("default") // só roda no ambiente padrão (dev)
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // ===============================
        // ADMIN DE TESTE
        // ===============================

        if (userRepository.findByEmail("admin@teste.com").isEmpty()) {

            User user = new User();
            user.setEmail("admin@teste.com");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRole(Role.USER);

            userRepository.save(user);
        }
        // ===============================
        // CLIENTE DE TESTE
        // ===============================
        if (userRepository.findByEmail("cliente@teste.com").isEmpty()) {

            User cliente = new User();
            cliente.setEmail("cliente@teste.com");
            cliente.setPassword(passwordEncoder.encode("123456"));
            cliente.setRole(Role.USER);

            userRepository.save(cliente);
        }
    }
    }

