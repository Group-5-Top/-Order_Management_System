package ru.tim_5.services;

import ru.tim_5.models.Customer;
import ru.tim_5.models.CustomerCategory;
import ru.tim_5.repositories.CuctomerRepository;

import java.util.List;

public class CustomerService {
    // new1
    private final CuctomerRepository customerRepositories;

    public CustomerService(CuctomerRepository customerRepositories) {
        this.customerRepositories = customerRepositories;
    }

    public Customer addCustomer(String name, CustomerCategory category) {
        Customer customer = new Customer(name, category);
        return customerRepositories.saveCustomers(customer);
    }

    public List<Customer> getAll(){
        return customerRepositories.findAllCustomers();
    }

    public Customer getProduct(int id) {
        return customerRepositories.findById(id);
    }
}
