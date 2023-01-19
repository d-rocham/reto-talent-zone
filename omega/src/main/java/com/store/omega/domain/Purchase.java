package com.store.omega.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Purchase {
    private String id;
    private LocalDateTime dateTime;
    private idType idType;
    private String cxId;
    private String customerName;
    private ArrayList<PurchasedProduct> purchasedProducts;
}
