package com.ecommerce.ecommerce.service;


public interface InventoryService {
    void discountStock(Long order);
    void increaseStock(Long order);
}
