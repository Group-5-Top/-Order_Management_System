package ru.tim_5.repositories;

import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.models.IdGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CuctomerRepository {
    private Integer countId;
    private final Path filePath;
    private String fileName = "customer.txt";

    public CuctomerRepository() {//ИЗМЕНИЛ
        this.filePath = Path.of(fileName);
        countId = 0;
        try {
            if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public Customer saveCustomers(Customer customer) {//МЕТОД СОХРАНЕНИЯ В ФАЙЛЫ
//        customer.setId(IdGenerator.generateID());
//        IdGenerator.generateID();
        try {
            Files.write(filePath, (customer + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return customer;
    }

    public List<Customer> findAllCustomer(){//МЕТОД ПОКАЗА ВСЕХ ПРОДУКТОВ НО ОН
        // НЕ ДОКОНЦА КОРЕКНО РАБОТАЕТ
        try {
            return Files.readAllLines(filePath).stream()
                    .map(l -> new Customer(l))
                    .toList();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
//    public List<Customer> findAllCustomers() {
//        return customers;
//    }

    public Customer findByIdCustomer(int id) throws CustomerNotFoundException {//ПОДРЕДАЧИЛ НАДО ПРОВЕРЯТЬ И ТЕСТИТЬ
        return  findAllCustomer().stream()
                .filter(customer -> customer.getID().equals(id))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
    }
}
