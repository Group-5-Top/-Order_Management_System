package ru.tim_5.services;

import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.enums.CustomerCategory;
import ru.tim_5.repositories.CustomerRepository;

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

    public Customer getCustomerId(String id) throws CustomerNotFoundException {
        return customerRepositories.findByIdCustomer(id);
    }



}
