package ru.tim_5.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.services.OrderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CustomerRepository {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
    private final Path filePath;
    private String fileName = "customer.txt";

    public CustomerRepository() {
        logger.debug("Creating new customer repository");
        this.filePath = Path.of(fileName);
        try {
            if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        }catch (IOException e){
            logger.error(e.getMessage());
        }
        logger.info("New customer repository created");
    }

    public Customer saveCustomers(Customer customer) {
        logger.debug("Saving customer information");
        //Метод сохраняет покупателя в файл
        try {
            Files.write(filePath, (customer + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        logger.info("New customer information saved");
        return customer;
    }

    public List<Customer> findAllCustomer(){
        logger.debug("Finding all customers");
        ////Метод получения листа покупателей из файла
        try {
            logger.info("Finding all customers");
            return Files.readAllLines(filePath).stream()
                    .map(Customer::new)
                    .toList();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Customer findByIdCustomer(String id) throws CustomerNotFoundException {
        logger.info("Finding customer with id {}", id);
        return  findAllCustomer().stream()
                .filter(customer -> customer.getID().equals(id))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Пользователь не найден: " + id));
    }
}
