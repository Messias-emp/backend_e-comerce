package com.ecommerce.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    // 🧑 Usuário que fez o pedido
    private String userEmail;
    // 💰 Valor total
   
    // ✅ Tipo correto para dinheiro
    @Column(nullable = false)
    private BigDecimal total;

    // 📦 Status do pedido

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 📅 Data do pedido
        private LocalDateTime createdAt;

    // 🔗 RELAÇÃO COM OS ITENS
    // Order.java
    //@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   // private List<OrderItem> items;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    }
 
 
  
 




