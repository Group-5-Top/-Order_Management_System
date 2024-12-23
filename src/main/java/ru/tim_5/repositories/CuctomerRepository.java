package ru.tim_5.repositories;

import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CuctomerRepository {

    private final List<Customer> customers;
    private Integer countId;

    public CuctomerRepository() {
        this.customers = new ArrayList<>();
        countId = 0;
    }

    public Customer saveCustomers(Customer customer) {
        customer.setId(++countId);

        if (customers.add(customer)){
            return customer;
        }
        return null;
    }
    public List<Customer> findAllCustomers() {
        return customers;
    }

    public Customer findById(int id) throws CustomerNotFoundException {
        return findAllCustomers().stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
    }
}
