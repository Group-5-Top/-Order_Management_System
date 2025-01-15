package ru.tim_5.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.enums.CustomerCategory;
import ru.tim_5.repositories.CustomerRepository;
import ru.tim_5.repositories.ProductRepository;

import java.util.List;

public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
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
        logger.info("get all customers");
        return customerRepositories.findAllCustomer();
    }

    public Customer getCustomerId(String id) throws CustomerNotFoundException {
        logger.info("get customer with id {}", id);
        return customerRepositories.findByIdCustomer(id);
    }



}
