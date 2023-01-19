package com.store.omega.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Purchase {
    private String id;
    private LocalDateTime dateTime;
    private com.store.omega.domain.idType idType;
    private String cxId;
    private String customerName;
    private ArrayList<PurchasedProduct> purchasedProducts;
}
