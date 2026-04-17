package com.ecommerce.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 📦 CheckoutItem
 * ----------------------------------------------------
 * Representa um item enviado no checkout.
 * Espelha o carrinho do frontend.
 */
@Getter
@Setter
public class CheckoutItem {

    // 🔑 Produto
    private Long productId;
    private String productName;

    // 📊 Quantidade escolhida
    private Integer quantity;

    // 💰 Preço unitário no momento da compra
    private BigDecimal price;
}
