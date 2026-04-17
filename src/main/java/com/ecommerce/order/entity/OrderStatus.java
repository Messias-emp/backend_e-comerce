package com.ecommerce.order.entity;


    
/**
 * 📌 Status do Pedido
 * Representa o estado do pedido no fluxo de negócio
 */
public enum OrderStatus {

    /**
     * Pedido criado, mas ainda não pago
     */
    PENDING,
    CREATED,
    CANCELED,


    /**
     * Pedido confirmado e pago
     */
    PAID
}

