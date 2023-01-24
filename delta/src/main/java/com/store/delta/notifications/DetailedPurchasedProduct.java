package com.store.delta.notifications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailedPurchasedProduct {
    private String id;
    private int amount;
    private String productName;
}
