package com.ecommerce.products;


import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do produto
    @Column(nullable = false)
    private String name;

    // Descrição detalhada
    @Column(length = 1000)
    private String description;
    
    // Preço com precisão financeira
    @Column(nullable = false)
    private BigDecimal price;

    //estoque
    private Integer stock;

    // URL da imagem
    private String imageUrl;

    // Controle simples de ativação
    private Boolean active = true;
    
    // 🔹 Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
     // construtor usado no DataLoader
    public Product(Long id, String name, String description,
               BigDecimal price, Integer stock,
               String imageUrl, Boolean active) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.imageUrl = imageUrl;
    this.active = active;
}
    

    // construtor vazio obrigatório
    public Product() {}
}
