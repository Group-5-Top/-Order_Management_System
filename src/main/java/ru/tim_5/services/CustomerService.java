package ru.tim_5.services;

import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.models.CustomerCategory;
import ru.tim_5.repositories.CustomerRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CustomerService {
    // new1
    private final CustomerRepository customerRepositories;

    public CustomerService(CustomerRepository customerRepositories) {
        this.customerRepositories = customerRepositories;
    }

    public Customer addCustomer(String name, CustomerCategory category) {
        Customer customer = new Customer(name, category);
        return customerRepositories.saveCustomers(customer);
    }

    public List<Customer> getAll(){
        // Получаем список клиентов
        return customerRepositories.findAllCustomer();
    }

    public Customer getCustomer(int id) throws CustomerNotFoundException {
        return customerRepositories.findByIdCustomer(id);
    }

}
