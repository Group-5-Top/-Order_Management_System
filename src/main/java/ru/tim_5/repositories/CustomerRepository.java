package ru.tim_5.repositories;

import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.models.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CustomerRepository {

    private final Path filePath;
    private String fileName = "customer.txt";

    public CustomerRepository() {
        this.filePath = Path.of(fileName);
        try {
            if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public Customer saveCustomers(Customer customer) {
        //Метод сохраняет покупателя в файл
        try {
            Files.write(filePath, (customer + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return customer;
    }





    public List<Customer> findAllCustomer(){
        ////Метод получения листа покупателей из файла
        try {
            return Files.readAllLines(filePath).stream()
                    .map(Customer::new)
                    .toList();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Customer findByIdCustomer(String id) throws CustomerNotFoundException {//ПОДРЕДАЧИЛ НАДО ПРОВЕРЯТЬ И ТЕСТИТЬ
        return  findAllCustomer().stream()
                .filter(customer -> customer.getID().equals(id))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Пользователь не найден: " + id));
    }
}
