package com.store.omega.domain.models;

import com.store.omega.business.businessobjects.PurchaseBO;
import com.store.omega.domain.generic.PurchasedProduct;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import com.store.omega.domain.generic.idType;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Document
@NoArgsConstructor
public class Purchase {
    @MongoId
    private String id;
    private LocalDateTime dateTime;
    private idType idType;
    private String cxId;
    private String customerName;
    private ArrayList<PurchasedProduct> purchasedProducts;

    public Purchase(PurchaseBO purchaseBO) {
        this.dateTime = purchaseBO.getDateTime();
        this.idType = purchaseBO.getIdType();
        this.cxId = purchaseBO.getCxId();
        this.customerName = purchaseBO.getCustomerName();
        this.purchasedProducts = purchaseBO.getPurchasedProducts();
    }


    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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
