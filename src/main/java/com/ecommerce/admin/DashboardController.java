package com.ecommerce.admin;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.*;

import com.ecommerce.auth.UserRepository;
import com.ecommerce.auth.dto.DashboardDTO;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.products.ProductRepository;

@RestController
@RequestMapping("/admin/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public DashboardController(
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository) {

        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public DashboardDTO getDashboard() {

        long totalUsers = userRepository.count();

        long totalProducts = productRepository.count();

        long totalOrders = orderRepository.count();

        BigDecimal totalRevenue =
                orderRepository.getTotalRevenue();

        return new DashboardDTO(
                totalUsers,
                totalProducts,
                totalOrders,
                totalRevenue
        );
    }
}