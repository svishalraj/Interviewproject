package com.interview.project;

import com.interview.project.beans.Address;
import com.interview.project.beans.Dimension;
import com.interview.project.beans.Item;
import com.interview.project.beans.Order;
import com.interview.project.inventory.Inventory;
import com.interview.project.process.OrderProcessor;
import com.interview.project.process.impl.OrderProcessorImpl;
import com.interview.project.rules.RulesEngine;
import com.interview.project.rules.impl.RulesEngineImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Client {
    public static void main(String... args) {
        final RulesEngine rulesEngine = new RulesEngineImpl();
        final Inventory inventory = new Inventory();
        final OrderProcessor orderProcessor = new OrderProcessorImpl(rulesEngine, inventory);

        Queue<Order> orders = createOrders();
        for (Order order : orders) {
            orderProcessor.process(order);
        }
    }

    public static Queue<Order> createOrders() {
        //priority queue will process the orders from older records to new records.
        PriorityQueue<Order> priorityQueue = new PriorityQueue<>((order1, order2) -> Long.compare(order2.getPurchaseTime(),
                order1.getPurchaseTime()));
        final Order order = new Order();
        Item item = new Item();
        Dimension dimension = new Dimension(1, 1, 1);
        item.setDimension(dimension);
        item.setProductId("iphone-13");
        item.setDescription("IPhone 13 max");

        order.setItem(item);
        order.setPurchaseTime(System.currentTimeMillis());
        order.setShipTo(new Address("street", "US", "98013"));

        priorityQueue.add(order);
        return priorityQueue;
    }
}
