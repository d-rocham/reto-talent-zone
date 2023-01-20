package com.store.omega.business.businessobjects;

import com.store.omega.business.dto.ProductDTO;
import com.store.omega.domain.models.Product;

public class ProductBO {
    private String id;
    private String name;
    private int inInventory;
    private boolean enabled;
    private int min;
    private int max;

    public ProductBO(ProductDTO rootProduct) {
        this.name = rootProduct.getName().toLowerCase();
        this.inInventory = Math.max(rootProduct.getInInventory(), 0);
        this.enabled = rootProduct.isEnabled();
        this.min = rootProduct.getMin();
        this.max = rootProduct.getMax();
    }

    public ProductBO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.inInventory = product.getInInventory();
        this.enabled = product.isEnabled();
        this.min = product.getMin();
        this.max = product.getMax();
    }

    public void setInInventory(int requestedPurchase) throws Exception {
        if (requestedPurchase > this.inInventory) {
            throw new Exception("Requested amount is higher than purchase");
        } else {
        this.inInventory = inInventory - requestedPurchase;

        }
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
