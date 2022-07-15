package com.interview.project.process.impl;

import com.interview.project.beans.Order;
import com.interview.project.inventory.Inventory;
import com.interview.project.process.OrderProcessor;
import com.interview.project.rules.RulesEngine;

/**
 * Implementation of Order.
 */
public class OrderProcessorImpl implements OrderProcessor {
    private RulesEngine rulesEngine;

    public OrderProcessorImpl(RulesEngine rulesEngine) {
        this.rulesEngine = rulesEngine;
    }

    /**
     * Method to process the orders.
     *
     * @param order
     */
    @Override
    public void process(Order order) {
        if (rulesEngine.canShip(order)) {
            switch (rulesEngine.canShipOutside(order)) {
                case AUTOMATIC:
                    System.out.println("Can be shipped automatically.");
                    break;
                default:
                    System.out.println("Cannot be shipped automatically");
            }
        } else {
            System.out.println("Not eligible to ship");
        }
    }
}
