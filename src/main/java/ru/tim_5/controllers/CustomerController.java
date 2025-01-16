package ru.tim_5.controllers;

import ru.tim_5.enums.CustomerCategory;
import ru.tim_5.models.Customer;
import ru.tim_5.services.CustomerService;
import java.util.Scanner;

public class CustomerController {
    private final CustomerService customerServices;

    Scanner sc = new Scanner(System.in);
    String name;
    CustomerCategory category;

    public CustomerController(CustomerService customerServices) {
        this.customerServices = customerServices;
    }

    /**
     * Метод добавления покупателей. Передаёт данные с консоли в метод добавления в CustomerService
     */
    public void addCustomer(){
        System.out.println("Введи имя покупателя: ");
        name = sc.next();
        System.out.println("Введи категорию покупателя(NEW, REGULAR, VIP): ");
        try {
            category = CustomerCategory.valueOf(sc.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Введенная категория некорректна. " +
                    "Пожалуйста, выберите одну из: NEW, REGULAR, VIP.");
        }
        String view = customerServices.addCustomer(name, category).toString();
        System.out.println(view);
    }

    /**
     * Метод, выводит всех покупателей в консоль. Получает List<Customer> из CustomerService
     */
    public void getAllCustomers(){
        customerServices.getAll().forEach((el)-> System.out.println("Покупатель: {" + el + "}"));
    }

    /**
     * Метод получения объекта покупателя по его ID.
     * @return объект Customer
     */
    public Customer getCustomer(){
        System.out.println("Введите ID покупателя: ");
        String id = sc.nextLine();
        System.out.println(customerServices.getCustomerId(id));
        return customerServices.getCustomerId(id);
    }
}
