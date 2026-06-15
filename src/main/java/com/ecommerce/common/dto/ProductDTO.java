package com.ecommerce.common.dto;

public class ProductDTO {

private Long id;
private String name;
private String description;
private String details;
private String imageUrl;
private double price;
private int stock;

    public ProductDTO(
            Long id,
            String name,
            String description,
            double price,
            int stock,
            String imageUrl,
            String details) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDetails() {
        return details;
    }
}