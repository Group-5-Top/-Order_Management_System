package ru.tim_5.controllers;

import ru.tim_5.enums.CustomerCategory;
import ru.tim_5.enums.OrderCategory;
import ru.tim_5.models.Customer;
import ru.tim_5.models.Product;
import ru.tim_5.repositories.CustomerRepository;
import ru.tim_5.repositories.ProductRepository;
import ru.tim_5.services.CustomerService;
import ru.tim_5.services.OrderService;
import ru.tim_5.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {

    private final OrderService orderService;
    private Customer customer;
    private Product product;
    private OrderCategory category;

    CustomerRepository customerRepositories = new CustomerRepository();
    CustomerService customerServices = new CustomerService(customerRepositories);
    CustomerController customerController = new CustomerController(customerServices);

    ProductRepository productRepository= new ProductRepository();
    ProductService productService = new ProductService(productRepository);
    ProductController productController = new ProductController(productService);

    Scanner sc = new Scanner(System.in);
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void addOrder() {

        System.out.println("Выбрать покупателя по ID");

        Customer customer1 = customerController.getCustomer();

        boolean stop = true;
        List <Product> listProduct = new ArrayList<>();
        while (stop) {
            System.out.println("Нажмите 1 для выбора товар по ID или нажмите 0 для завершения: ");
            int start = sc.nextInt();

            switch (start) {
                case 1 : listProduct.add(productController.getProductById());
                sc.nextLine();
                    break;
                case 0 : stop = false;
                    break;

                default : System.out.println("Такой команды нет!");
            }
        }


        System.out.println("Введи категорию заказа( NEW, PROCESSING, COMPLETED, CANCELLED): ");
        try {
            category = OrderCategory.valueOf(sc.next());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Введенная категория некорректна. " +
                    "Пожалуйста, выберите одну из: NEW, PROCESSING, COMPLETED, CANCELLED.");
        }

        String view = orderService.addOrder(customer1, listProduct, category ).toString();
        System.out.println(view);
    }

    public void getAllOrders() {
            // Выводим клиентов на экран
            orderService.getAll().forEach(System.out::println);

    }

    }




