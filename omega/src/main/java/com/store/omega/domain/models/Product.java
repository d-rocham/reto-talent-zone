package com.store.omega.domain.models;

import com.store.omega.business.businessobjects.ProductBO;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Product {
    @MongoId
    private String id;
    private String name;
    private int inInventory;
    private boolean enabled;
    private int min;
    private int max;

    public Product(ProductBO productBO) {
        this.name = productBO.getName();
        this.inInventory = productBO.getInInventory();
        this.enabled = productBO.isEnabled();
        this.min = productBO.getMin();
        this.max = productBO.getMax();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInInventory() {
        return inInventory;
    }

    public void setInInventory(int inInventory) {
        this.inInventory = inInventory;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
