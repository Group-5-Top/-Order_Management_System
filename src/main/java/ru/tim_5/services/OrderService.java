package ru.tim_5.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.enums.OrderCategory;
import ru.tim_5.exeptions.OrderNotFoundException;
import ru.tim_5.models.Order;
import ru.tim_5.repositories.OrderRepository;

import java.util.List;
import java.util.Scanner;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepositories;
    Scanner scanner = new Scanner(System.in);

    public OrderService(OrderRepository orderRepositories) {
        this.orderRepositories = orderRepositories;
    }

    public Order addOrder(String customerID, List<String> productsID, OrderCategory category) {
        Order order = new Order(customerID, productsID, category);
        return orderRepositories.saveOrder(order);
    }

    /**
     * Метод для получения списка заказов из OrderRepositories.
     *
     * @return List<Order>
     */
    public List<Order> getListOrder() throws RuntimeException {
        return orderRepositories.findAllOrder();
    }

    public Order getOrderId(String id) throws OrderNotFoundException {
        return orderRepositories.findByIdOrder(id);
    }

    public void changeOrderCategory(String id, int lineNumber){
        logger.debug("Старт: Изменение статуса заказа.");
        Order order =  orderRepositories.findByIdOrder(id);
        System.out.println("Введите новый статус заказа: NEW, PROCESSING, COMPLETED, CANCELLED");
        order.setCategory(OrderCategory.valueOf(scanner.next().toUpperCase()));
        String content = order.toString();
        orderRepositories.replaceLineInFile(orderRepositories.getFilePath(), lineNumber, content);
        logger.info("Статус заказа успешно изменён.");
    }
}
