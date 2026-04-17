package com.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Teste básico de contexto.
 * 
 * Objetivo:
 * - Validar se o contexto Spring sobe corretamente
 * - Garantir que o build Maven não quebre
 * - Servir como base para testes futuros
 */
@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        // Se o contexto subir, o teste passa
    }
}
