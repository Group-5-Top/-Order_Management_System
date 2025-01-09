package ru.tim_5.repositories;

import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.models.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class OrderRepository {
    private final Path filePath;
    private String fileName = "order.txt";

    public OrderRepository() {
        this.filePath = Path.of(fileName);
        try {
            if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public Order saveOrder(Order order) {
        //Метод сохраняет покупателя в файл
        try {
            Files.write(filePath, (order + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return order;
    }

    public List<Order> findAllOrder(){
        ////Метод получения листа покупателей из файла
        try {
            return Files.readAllLines(filePath).stream()
                    .map(Order::new )
                    .toList();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Order findByIdOrder(String id) throws CustomerNotFoundException {//ПОДРЕДАЧИЛ НАДО ПРОВЕРЯТЬ И ТЕСТИТЬ
        return  findAllOrder().stream()
                .filter(customer -> customer.getID().equals(id))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Заказ не найден: " + id));
    }





}
