package com.interview.project.rules.impl;

import com.interview.project.beans.Item;
import com.interview.project.beans.Order;
import com.interview.project.beans.TypeOfOrder;
import com.interview.project.inventory.Inventory;
import com.interview.project.rules.RulesEngine;

import java.util.HashSet;
import java.util.Set;

/**
 * Ideally rules engine product can be used, but because of time constraint going through this approach.
 */
public class RulesEngineImpl implements RulesEngine {

    private static String COUNTRY = "US";
    private static int LENGTH = 6;
    private static int BREADTH = 6;
    private static int HEIGHT = 4;
    private Inventory inventory;

    public RulesEngineImpl(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Check if the order can be shipped.
     * @param order details.
     * @return
     */
    public boolean canShip(Order order) {
        boolean ship = true;
        for (Item item : order.getItem()) {
            Set<Item> visited = new HashSet<>();
            if (!checkDependentShipments(item.getDependsOn(), visited)) {
                System.out.println("Cannot ship, as dependents shipment is not allowed");
                ship = false;
                break;
            }
        }
        return ship;
    }

    /**
     * Validates if the shipment belongs to outside of the country.
     *
     * @param order details.
     * @return status if the item can be shipped outside.
     */
    public TypeOfOrder canShipOutside(final Order order) {
        return COUNTRY.equalsIgnoreCase(order.getShipTo().getCountry()) ? TypeOfOrder.AUTOMATIC : TypeOfOrder.MANUAL;
    }

    /**
     * Check recursively all the dependents items.
     * @param item
     * @param visited
     * @return
     */
    private boolean checkDependentShipments(final Item item, Set<Item> visited) {
        if (item == null || visited.contains(item)) {
            return true;
        }

        visited.add(item);

        if (!inventory.hasInventory(item)) {
            return false;
        }

        return checkDependentShipments(item.getDependsOn(), visited);
    }
}
