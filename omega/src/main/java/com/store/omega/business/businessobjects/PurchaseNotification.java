package com.store.omega.business.businessobjects;

import com.store.omega.domain.generic.idType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PurchaseNotification {

    private String id;
    private String dateTime;
    private String idType;
    private String cxId;
    private String customerName;
    private ArrayList<DetailedPurchasedProduct> purchasedProducts;

    public PurchaseNotification() {
        this.purchasedProducts = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdType(idType idType) {
        this.idType = idType.toString();
    }

    public void setCxId(String cxId) {
        this.cxId = cxId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime.toString();
    }

    public void appendDetailedProduct(DetailedPurchasedProduct detailedPurchasedProduct) {
        this.purchasedProducts.add(detailedPurchasedProduct);
    }


}
