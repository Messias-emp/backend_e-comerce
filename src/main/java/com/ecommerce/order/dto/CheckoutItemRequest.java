package com.ecommerce.order.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 🛒 Item individual do pedido
 * Representa cada produto enviado pelo front
 */
@Data
public class CheckoutItemRequest {

    private Long productId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
