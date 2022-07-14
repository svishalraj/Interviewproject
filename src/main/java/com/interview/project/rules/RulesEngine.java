package com.interview.project.rules;

import com.interview.project.beans.Item;

/**
 * Contains the rules which has to be applied on the shipment.
 */
public interface RulesEngine {
    /**
     * Validates if the shipment belongs to outside of the country.
     * @param item details.
     * @return status if the item can be shipped outside.
     */
    boolean canShipOutside(Item item);

    /**
     * Validates if the shipment can fit inside a box.
     * @param item details.
     * @return status if the item can fit inside a box.
     */
    boolean canFitInBox(Item item);

    /**
     * Checks the dependency of the item on others.
     * @param item details.
     * @return status if the item can be shipped.
     */
    boolean checkDependentShipments(Item item);
}
