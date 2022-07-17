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

/**
 * Order are prioritised based on the timestamp of the order request
 * Order consists of independent and dependent items. 
 * Requested order items are primarily verified across inventory
 *     INDEPENDENT items : 
 * 				If AVAILABLE : ordered item is will be processed with the next criteria
 * 				If UNAVAILABLE:  order status is updated to OUT_OF_STOCK
 *     DEPENDENT items:
 *              If all the dependent items are  AVAILABLE :  ordered item is will be processed with the next criteria
 *              If any of the dependent item are UNAVAILABLE:  order status is updated to OUT_OF_STOCK
 * If an international order request has been received
 * 				If item AVAILABLE : order status is updated to NEED_FURTHER_ASSISTANCE
 * 						UNAVAILABLE : Order status is updated to OUT_OF_STOCK
 * To maintain minimum number of shipping boxes
 * 				based on theoretical volumes of the items being shipped . 
 * 				The number of boxes generated may be a variable and is dependent on the person packaging the boxes
 * If one dependent item is OUT OF STOCK within the order, Order  Status will be updated to OUT_OF_STOCK
 * If all the items within the order meet the above criteria's, Order Status is Updated to "CAN BE SHIPPED".
 *
 *
 */

public class Client {
    		
    public static void main(String... args) {
        final Inventory inventory = new Inventory();
        final RulesEngine rulesEngine = new RulesEngineImpl(inventory);
        final OrderProcessor orderProcessor = new OrderProcessorImpl(rulesEngine);

        Queue<Order> orders = createOrders();
        for (Order order : orders) {
        	System.out.println(order);
            orderProcessor.process(order);
            System.out.println(order);
        }
    }
/**
 * Adding placed orders into the Queue
 * The proposed scenario is 
 * 					Iphone 13 is dependent on iphone-wire.
 *  				Iphone xr and iphone wire is an independent item
 * @return
 */
    public static Queue<Order> createOrders() {
        //priority queue will process the orders from older records to new records.
        PriorityQueue<Order> priorityQueue = new PriorityQueue<>((order1, order2) -> Long.compare(order2.getPurchaseTime(),
                order1.getPurchaseTime()));
        final Order order = new Order();
        List<Item> listItems = new ArrayList<>();
        Item item = new Item();
        Dimension dimension = new Dimension(3, 3, 4);
        item.setDimension(dimension);
        item.setProductId("iphone-13");
        item.setDescription("IPhone 13 ");
        item.setQuantity(3);
        listItems.add(item);

        Item item1 = new Item();
        dimension = new Dimension(3, 3, 2);
        item1.setDimension(dimension);
        item1.setProductId("iphone-wire");
        item1.setQuantity(3);
       listItems.add(item1);


       Item item2 = new Item();
       dimension = new Dimension(1, 1, 2);
       item2.setDimension(dimension);
       item2.setProductId("iphone-xs");
       item2.setQuantity(1);
       listItems.add(item2);

       
        order.setItems(listItems);
        order.setPurchaseTime(System.currentTimeMillis());
        order.setShipTo(new Address("street", "US", "98013"));

        priorityQueue.add(order);
        return priorityQueue;
    }
}
