
package com.ecommerce.order.model;

import java.math.BigDecimal;

/**
 * 📦 DTO / Model de API
 * Usado para comunicação com o frontend
 * NÃO representa tabela do banco
 */
public class OrderModel {

    private Long id;
    private BigDecimal total;
    private String status;

    // getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
