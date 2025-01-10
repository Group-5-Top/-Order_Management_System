package ru.tim_5.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.exeptions.OrderNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.models.Order;
import ru.tim_5.services.OrderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final Path filePath;
    private String fileName = "order.txt";

    public OrderRepository() {
        logger.info("Creating OrderRepository");
        this.filePath = Path.of(fileName);
        try {
            if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        }catch (IOException e){
            logger.error(e.getMessage());
        }
        logger.info("OrderRepository created");
    }

    public Order saveOrder(Order order) {
        logger.debug("Saving order: " + order);
        //Метод сохраняет покупателя в файл
        try {
            Files.write(filePath, (order + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        logger.info("Order saved: " + order);
        return order;
    }

    public List<Order> findAllOrder(){
        logger.debug("Finding all orders");
        ////Метод получения листа покупателей из файла
        try {
            logger.info("Finding all orders");
            return Files.readAllLines(filePath).stream()
                    .map(Order::new )
                    .toList();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Order findByIdOrder(String id) throws OrderNotFoundException {
        logger.info("Finding order by id: " + id);
        return  findAllOrder().stream()
                .filter(order -> order.getID().equals(id))
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException("Заказ не найден: " + id));
    }





}
