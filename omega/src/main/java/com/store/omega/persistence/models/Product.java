package com.store.omega.persistence.models;

public class Product {
    private String id;
    private String name;
    private int inInventory;
    private boolean enabled;
    private int min;
    private int max;

    public Product(String id, String name, int inInventory, boolean enabled, int min, int max) {
        this.id = id;
        this.name = name;
        this.inInventory = inInventory;
        this.enabled = enabled;
        this.min = min;
        this.max = max;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
