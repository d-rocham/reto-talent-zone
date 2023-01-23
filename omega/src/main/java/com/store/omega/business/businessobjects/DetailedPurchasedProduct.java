package com.store.omega.business.businessobjects;

public class DetailedPurchasedProduct {
    private String id;
    private int amount;
    private String productName;

    public DetailedPurchasedProduct(String id, int amount, String productName) {
        this.id = id;
        this.amount = amount;
        this.productName = productName;
    }
}
