
package com.ecommerce.products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    // CREATE
    public Product create(Product product) {
        return repository.save(product);
    }

    // READ - listar todos
    public List<Product> findAll() {
        return repository.findAll();
    }

    // READ - buscar por ID
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    // UPDATE
    public Product update(Long id, Product updatedProduct) {
        Product existing = findById(id);

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setImageUrl(updatedProduct.getImageUrl());
        existing.setActive(updatedProduct.getActive());

        return repository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
