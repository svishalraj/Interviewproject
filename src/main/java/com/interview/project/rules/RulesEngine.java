package com.interview.project.rules;

import com.interview.project.beans.Item;
import com.interview.project.beans.Order;
import com.interview.project.beans.TypeOfOrder;

/**
 * Contains the rules which has to be applied on the shipment.
 */
public interface RulesEngine {
    /**
     * Validates if it can be shipped.
     *
     * @param order details.
     * @return status if the item can be shipped.
     */
    boolean canShip(Order order);

    /**
     * Check if it can be shipped outside of US.
     *
     * @param order
     * @return type of order manual or automatic processing.
     */
    TypeOfOrder canShipOutside(Order order);

    /**
     * Get the number of packages.
     * @param order
     * @return number of packages.
     */
    int getNumberOfPackages(Order order);
}