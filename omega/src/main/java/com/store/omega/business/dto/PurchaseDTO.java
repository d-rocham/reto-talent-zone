package com.store.omega.business.dto;

import com.store.omega.domain.generic.idType;
import com.store.omega.domain.generic.PurchasedProduct;
import com.store.omega.domain.models.Purchase;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PurchaseDTO {

    private String id;
    private LocalDateTime dateTime;

    private final idType idType;
    private final String cxId;
    private final String customerName;
    private final ArrayList<PurchasedProduct> purchasedProducts;

    public PurchaseDTO(idType idType, String cxId, String customerName, ArrayList<PurchasedProduct> purchasedProducts) {
        this.idType = idType;
        this.cxId = cxId;
        this.customerName = customerName;
        this.purchasedProducts = purchasedProducts;
    }

    public PurchaseDTO(Purchase purchase) {
        this.id = purchase.getId();
        this.dateTime = purchase.getDateTime();
        this.idType = purchase.getIdType();
        this.cxId = purchase.getCxId();
        this.customerName = purchase.getCustomerName();
        this.purchasedProducts = purchase.getPurchasedProducts();
    }

    public idType getIdType() {
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
