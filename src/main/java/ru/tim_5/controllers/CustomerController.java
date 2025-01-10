package ru.tim_5.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.enums.CustomerCategory;
import ru.tim_5.models.Customer;
import ru.tim_5.repositories.ProductRepository;
import ru.tim_5.services.CustomerService;

import java.util.Scanner;

public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerServices;

    Scanner sc = new Scanner(System.in);
    String name;
    CustomerCategory category;

    public CustomerController(CustomerService customerServices) {
        this.customerServices = customerServices;
    }

    public void addCustomer(){
        logger.debug("Start add customer");
        System.out.println("Введи имя покупателя: ");
        name = sc.next();
        System.out.println("Введи категорию покупателя(NEW, REGULAR, VIP): ");
        try {
            category = CustomerCategory.valueOf(sc.next());
        } catch (IllegalArgumentException e) {
            logger.error("Ошибка: Введенная категория некорректна. " +
                    "Пожалуйста, выберите одну из: NEW, REGULAR, VIP.");
        }

        String view = customerServices.addCustomer(name, category).toString();
        System.out.println(view);
        logger.info("Finish add customer");
    }

    public void getAllCustomers(){
        logger.debug("Start get all customers");
        // Выводим клиентов на экран
        customerServices.getAll().forEach((el)-> System.out.println("Покупатель: {" + el + "}"));
        logger.info("Finish get all customers");
    }



    public Customer getCustomer(){
        //Выводим клиента по ID
        System.out.println("Введите ID покупателя: ");
        String id = sc.nextLine();
        System.out.println(customerServices.getCustomerId(id));
        return customerServices.getCustomerId(id);
    }
}
