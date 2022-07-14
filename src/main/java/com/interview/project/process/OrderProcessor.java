package com.interview.project.process;

import com.interview.project.beans.Order;

/**
 * Processes the order, of each item.
 */
public interface OrderProcessor {
    /**
     * Method to process the orders.
     * @param order
     */
    public void process(Order order);
}
