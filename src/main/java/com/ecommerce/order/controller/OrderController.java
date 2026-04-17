package com.ecommerce.order.controller;


import com.ecommerce.order.dto.CheckoutRequest;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.service.OrderService;
import com.ecommerce.sercurity.SecurityUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



/**
 * 🧾 OrderController
 * ----------------------------------------------------
 * Camada de API (REST)
 * Responsável por expor endpoints de pedido (Order)
    */
@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
        
    }

    // 🧾 Criar pedido (checkout)

  @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(
    @RequestBody CheckoutRequest request) {
    return ResponseEntity.ok(orderService.createOrder(request));
}



    // 🔍 Buscar pedido por ID
@GetMapping("/{id}")
public ResponseEntity<Order> getById(@PathVariable Long id) {

    String email = SecurityUtils.getAuthenticatedEmail();
    Order order = orderService.findByIdAndUserEmail(id, email);

    if (order == null) {
        return ResponseEntity.notFound().build();
        
    }

    return ResponseEntity.ok(order);
}
    // 💳 Simular pagamento
    @PutMapping("/{id}/pay")
    public ResponseEntity<Order> pay(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.markAsPaid(id));
    }

    // 📦 Listar pedidos do usuário logado (JWT)

@GetMapping("/my-orders")
public ResponseEntity<List<Order>> getMyOrders() {
    
    String email = SecurityUtils.getAuthenticatedEmail();

    List<Order> orders = orderService.findByUserEmail(email);

    return ResponseEntity.ok(orders);
}

}
