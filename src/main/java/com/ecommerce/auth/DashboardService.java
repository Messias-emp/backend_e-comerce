package com.ecommerce.auth;

import org.springframework.stereotype.Service;

import com.ecommerce.auth.dto.DashboardDTO;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.products.ProductRepository;

@Service
public class DashboardService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public DashboardService(

        UserRepository userRepository, 
        OrderRepository orderRepository,
        ProductRepository productRepository) {

        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

public DashboardDTO getStats() {

return new DashboardDTO(
    userRepository.count(),
    productRepository.count(),
    orderRepository.count(),
    orderRepository.getTotalRevenue()
);      
}   
}   
