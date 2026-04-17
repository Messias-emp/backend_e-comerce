package com.ecommerce.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 🧾 CheckoutRequest
 * ----------------------------------------------------
 * DTO recebido no momento do checkout.
 * Representa o pedido enviado pelo frontend.
 */
@Getter
@Setter
public class CheckoutRequest {

    // 🧑 Identificação do usuário
    private Long userId;
    private String userEmail;

    // 📦 Itens do carrinho
    private List<CheckoutItem> items;
}
