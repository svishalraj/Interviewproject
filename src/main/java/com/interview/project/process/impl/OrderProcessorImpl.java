package com.interview.project.process.impl;

import com.interview.project.beans.Order;
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
        if (!rulesEngine.checkDependentShipments(order.getItem())) {
            System.out.println("Cannot ship, as dependents shipment is not allowed");
            return;
        }

        if (!rulesEngine.canFitInBox(order.getItem())) {
            System.out.println("Cannot ship, as it cannot fit in the box");
            return;
        }

        if(rulesEngine.canShipOutside(order.getItem())) {
            System.out.println("Manually handle order");
        } else {
            System.out.println("Automatically handle order");
        }
    }
}
