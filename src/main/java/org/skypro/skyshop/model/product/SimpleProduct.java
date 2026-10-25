package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}


