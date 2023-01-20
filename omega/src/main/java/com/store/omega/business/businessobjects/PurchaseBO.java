package com.store.omega.business.businessobjects;

import com.store.omega.business.dto.ProductDTO;
import com.store.omega.business.dto.PurchaseDTO;
import com.store.omega.domain.generic.PurchasedProduct;
import com.store.omega.domain.generic.idType;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class PurchaseBO {
    private LocalDateTime dateTime;
    private idType idType;
    private String cxId;
    private String customerName;
    private ArrayList<PurchasedProduct> purchasedProducts;

    public PurchaseBO(PurchaseDTO purchaseDTO) {
        this.dateTime = LocalDateTime.now();
        this.idType = purchaseDTO.getIdType();
        this.cxId = purchaseDTO.getCxId();
        this.customerName = purchaseDTO.getCustomerName().toLowerCase(Locale.ROOT);
        this.purchasedProducts = purchaseDTO.getPurchasedProducts();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public com.store.omega.domain.generic.idType getIdType() {
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
