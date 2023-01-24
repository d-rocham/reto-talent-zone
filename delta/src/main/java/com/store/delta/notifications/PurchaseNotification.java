package com.store.delta.notifications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseNotification {
    private String id;
    private String dateTime;
    private String idType;
    private String cxId;
    private String customerName;
    private ArrayList<DetailedPurchasedProduct> purchasedProducts;

}
