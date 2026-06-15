package com.ecommerce.auth.dto;

import java.math.BigDecimal;

public record DashboardDTO(
        Long totalUsers,
        Long totalProducts,
        Long totalOrders,
        BigDecimal totalRevenue
) {}