package ru.tim_5.controllers;

import ru.tim_5.models.CustomerCategory;
import ru.tim_5.services.CustomerService;

import java.util.Scanner;

public class CustomerController {
    // new1
    Scanner sc = new Scanner(System.in);
    String name;
    CustomerCategory category;

    private final CustomerService customerServices;

    public CustomerController(CustomerService customerServices) {
        this.customerServices = customerServices;
    }

    public void addCustomer(){
        System.out.println("Введи имя покупателя: ");
        name = sc.next();
        System.out.println("Введи категорию покупателя(NEW, REGULAR, VIP): ");
        category = CustomerCategory.valueOf(sc.next());
        String view = customerServices.addCustomer(name, category).toString();
        System.out.println(view);
    }

    public void getAllCustomers(){
        String view = customerServices.getAll().toString();
        System.out.println(view);
    }
}
