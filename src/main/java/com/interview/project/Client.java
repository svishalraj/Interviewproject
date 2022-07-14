package com.interview.project;

import com.interview.project.beans.Order;
import com.interview.project.process.OrderProcessor;
import com.interview.project.process.impl.OrderProcessorImpl;
import com.interview.project.rules.RulesEngine;
import com.interview.project.rules.impl.RulesEngineImpl;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String... args) {
        final RulesEngine rulesEngine = new RulesEngineImpl();
        final OrderProcessor orderProcessor = new OrderProcessorImpl(rulesEngine);
        List<Order> orders = new ArrayList<>();
        for (Order order : orders) {
            orderProcessor.process(order);
        }
    }
}
