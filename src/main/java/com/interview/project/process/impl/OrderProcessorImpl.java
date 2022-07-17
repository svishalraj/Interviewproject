package com.interview.project.process.impl;

import com.interview.project.beans.Order;
import com.interview.project.inventory.Inventory;
import com.interview.project.process.OrderProcessor;
import com.interview.project.rules.RulesEngine;

/**
 * Implementation of Order Processing and the order status is updated accordingly .
 */
public class OrderProcessorImpl implements OrderProcessor {
    private  final RulesEngine rulesEngine;
    private final String CAN_BE_SHIPPED = "Can be shipped automatically.";
    private final String CANNOT_BE_SHIPPED = "Cannot be shipped automatically";
    private final String NEED_FURTHER_ASSISTANCE = "Need further assistance to be processed";

    
    public OrderProcessorImpl(RulesEngine rulesEngine) {
        this.rulesEngine = rulesEngine;
    }

    /**
     * Method to process the orders.
     *
     * @param order
     */
    @Override
    public void process(final Order order) {
        if (rulesEngine.canShip(order)) {
            switch (rulesEngine.canShipOutside(order)) {
                case AUTOMATIC:
                	order.setStatus(CAN_BE_SHIPPED);
                	order.setPackageBoxes(rulesEngine.getNumberOfPackages(order));
                    break;
                case MANUAL:
                	order.setStatus(NEED_FURTHER_ASSISTANCE);
                    order.setPackageBoxes(rulesEngine.getNumberOfPackages(order));
                	break;
                default:
                    System.out.println(CANNOT_BE_SHIPPED);
            }
        } else {
        	System.out.println("Not eligible to ship");
        }
    }
}
