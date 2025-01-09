package ru.tim_5.controllers;

import ru.tim_5.enums.CustomerCategory;
import ru.tim_5.models.Customer;
import ru.tim_5.services.CustomerService;

import java.util.Scanner;

public class CustomerController {
    // new1
    private final CustomerService customerServices;

    Scanner sc = new Scanner(System.in);
    String name;
    CustomerCategory category;

    public CustomerController(CustomerService customerServices) {
        this.customerServices = customerServices;
    }

    public void addCustomer(){
        System.out.println("Введи имя покупателя: ");
        name = sc.next();
        System.out.println("Введи категорию покупателя(NEW, REGULAR, VIP): ");
        try {
            category = CustomerCategory.valueOf(sc.next());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Введенная категория некорректна. " +
                    "Пожалуйста, выберите одну из: NEW, REGULAR, VIP.");
        }

        String view = customerServices.addCustomer(name, category).toString();
        System.out.println(view);
    }

    public void getAllCustomers(){
        // Выводим клиентов на экран
        customerServices.getAll().forEach((el)-> System.out.println("Покупатель: {" + el + "}"));
    }



    public Customer getCustomer(){
        //Выводим клиента по ID
        System.out.println("Введите ID покупателя: ");
        String id = sc.nextLine();
        System.out.println(customerServices.getCustomerId(id));
        return customerServices.getCustomerId(id);
    }
}
