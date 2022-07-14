package com.interview.project.inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * The class is static which contains the inventory.
 */
public class Inventory {
    private List<String> productIds;

    public Inventory() {
        productIds = new ArrayList<>();
        productIds.add("iphone-13");
    }

    public boolean hasInventory(String productId) {
        return productIds.contains(productId);
    }
}
