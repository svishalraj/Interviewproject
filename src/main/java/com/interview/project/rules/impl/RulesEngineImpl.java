package com.interview.project.rules.impl;

import com.interview.project.beans.Item;
import com.interview.project.rules.RulesEngine;

public class RulesEngineImpl implements RulesEngine {
    /**
     * Validates if the shipment belongs to outside of the country.
     *
     * @param item details.
     * @return status if the item can be shipped outside.
     */
    @Override
    public boolean canShipOutside(Item item) {
        return false;
    }

    /**
     * Validates if the shipment can fit inside a box.
     *
     * @param item details.
     * @return status if the item can fit inside a box.
     */
    @Override
    public boolean canFitInBox(Item item) {
        return false;
    }

    /**
     * Checks the dependency of the item on others.
     *
     * @param item details.
     * @return status if the item can be shipped.
     */
    @Override
    public boolean checkDependentShipments(Item item) {
        return false;
    }
}
