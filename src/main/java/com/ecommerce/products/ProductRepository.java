//Camada de persistência, limpa e direta ao ponto.

package com.ecommerce.products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
