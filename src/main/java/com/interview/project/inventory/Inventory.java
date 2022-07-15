package com.interview.project.inventory;

import com.interview.project.beans.Item;

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
        productIds.add("iphone-wire");
    }

    public boolean hasInventory(List<Item> items) {
        boolean found = true;
        for (Item item : items) {
            if(!productIds.contains(item.getProductId())) {
                found = false;
                break;
            }
        }
        return found;
    }

    public boolean hasInventory(Item item) {
        return productIds.contains(item.getProductId());
    }
}
