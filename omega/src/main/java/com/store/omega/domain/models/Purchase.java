package com.store.omega.domain.models;

import com.store.omega.domain.idType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Purchase {
    private String id;
    private LocalDateTime dateTime;
    private com.store.omega.domain.idType idType;
    private String cxId;
    private String customerName;
    private ArrayList<PurchasedProduct> purchasedProducts;

    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public com.store.omega.domain.idType getIdType() {
        return idType;
    }

    public String getCxId() {
        return cxId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<PurchasedProduct> getPurchasedProducts() {
        return purchasedProducts;
    }
}
