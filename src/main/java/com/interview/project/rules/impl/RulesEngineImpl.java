package com.interview.project.rules.impl;

import com.interview.project.beans.Item;
import com.interview.project.beans.Order;
import com.interview.project.beans.TypeOfOrder;
import com.interview.project.inventory.Inventory;
import com.interview.project.rules.RulesEngine;

/**
 * Ideally rules engine product can be used, but because of time constraint going through this approach.
 */
public class RulesEngineImpl implements RulesEngine {

    private static String COUNTRY = "US";
    private static int LENGTH = 6;
    private static int BREADTH = 6;
    private static int HEIGHT = 4;

    public boolean canShip(Order order) {

        if (!checkDependentShipments(order.getItem().getDependsOn())) {
            System.out.println("Cannot ship, as dependents shipment is not allowed");
            return false;
        }

        if (!canFitInBox(order.getItem())) {
            System.out.println("Cannot ship, as it cannot fit in the box");
            return false;
        }

        return true;
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
     * Validates if the shipment can fit inside a box.
     *
     * @param item details.
     * @return status if the item can fit inside a box.
     */
    private boolean canFitInBox(final Item item) {
        return item.getDimension().getLength() <= LENGTH &&
                item.getDimension().getBreadth() <= BREADTH &&
                 item.getDimension().getHeight() <= HEIGHT;
    }

    /**
     * Checks the dependency of the item on others.
     *
     * @param item details.
     * @return status if the item can be shipped.
     */
    private boolean checkDependentShipments(final Item item) {
        if (item == null) {
            return true;
        }

        return canFitInBox(item) && checkDependentShipments(item.getDependsOn());
    }
}
