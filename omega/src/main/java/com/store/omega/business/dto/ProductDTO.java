package com.store.omega.business.dto;

import com.store.omega.domain.models.Product;

public class ProductDTO {
    private String id;
    private final String name;
    private final int inInventory;
    private final boolean enabled;
    private final int min;
    private final int max;

    public ProductDTO(String name, int inInventory, boolean enabled, int min, int max) {
        this.name = name;
        this.inInventory = inInventory;
        this.enabled = enabled;
        this.min = min;
        this.max = max;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.inInventory = product.getInInventory();
        this.enabled = product.isEnabled();
        this.min = product.getMin();
        this.max = product.getMax();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInInventory() {
        return inInventory;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
