package ru.tim_5.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.enums.OrderCategory;
import ru.tim_5.exeptions.OrderNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.models.Order;
import ru.tim_5.models.Product;
import ru.tim_5.repositories.OrderRepository;

import java.util.List;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepositories;

    public OrderService(OrderRepository orderRepositories) {
        this.orderRepositories = orderRepositories;
    }

    public Order addOrder(Customer customer, List<Product> products, OrderCategory category) {
        logger.debug("Adding new order");
        Order order = new Order(customer, products, category);
        logger.info("Order created");
        return orderRepositories.saveOrder(order);
    }

    public List<Order> getAll(){
        logger.debug("Getting all orders");
        // Получаем список клиентов
        logger.info("Getting all orders");
        return orderRepositories.findAllOrder();
    }

    public Order getOrderId(String id) throws OrderNotFoundException {
        logger.info("Getting order with id {}", id);
        return orderRepositories.findByIdOrder(id);
    }


}
