package com.ecommerce.products;

import org.springframework.web.bind.annotation.*;
import java.util.List;

/* 
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ➕ Criar produto
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    // 📋 Listar produtos
    @GetMapping
    public List<Product> findAll() {
        return service.findAll();
    }

    // 🔍 Buscar por ID
    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // ✏️ Atualizar
    @PutMapping("/{id}")
    public Product update(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        return service.update(id, product);
    }

    // 🗑️ Deletar
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}*/


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    // CREATE
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(service.create(product));
    }

    // READ - todos
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // READ - por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        return ResponseEntity.ok(service.update(id, product));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
