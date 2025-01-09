package ru.tim_5.services;

import ru.tim_5.enums.CustomerCategory;
import ru.tim_5.enums.OrderCategory;
import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.models.Order;
import ru.tim_5.models.Product;
import ru.tim_5.repositories.CustomerRepository;
import ru.tim_5.repositories.OrderRepository;

import java.util.List;

public class OrderService {

    private final OrderRepository orderRepositories;

    public OrderService(OrderRepository orderRepositories) {
        this.orderRepositories = orderRepositories;
    }

    public Order addOrder(Customer customer, List<Product> products, OrderCategory category) {
        Order order = new Order(customer, products, category);
        return orderRepositories.saveOrder(order);
    }



    public List<Order> getAll(){
        // Получаем список клиентов
        return orderRepositories.findAllOrder();
    }

    public Order getOrderId(String id) throws CustomerNotFoundException {
        return orderRepositories.findByIdOrder(id);
    }


}
