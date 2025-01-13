package ru.tim_5.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.enums.CustomerCategory;
import ru.tim_5.enums.OrderCategory;
import ru.tim_5.models.Customer;
import ru.tim_5.models.Order;
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

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

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
        logger.debug("Start add order");
        System.out.println("Выбрать покупателя по ID");

        Customer customer1 = customerController.getCustomer();

        boolean stop = true;
        List <String> listProductID = new ArrayList<>();
        while (stop) {
            System.out.println("Нажмите 1 для выбора товар по ID или нажмите 0 для завершения: ");
            int start = sc.nextInt();
            switch (start) {
                case 1 :
                    System.out.println(listProductID.add(productController.getProductById().getId()));
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
            logger.error("\"Ошибка: Введенная категория некорректна." +
                    "Пожалуйста, выберите одну из: NEW, PROCESSING, COMPLETED, CANCELLED.\n");

        }

        String view = orderService.addOrder(customer1.getID(), listProductID, category ).toString();
        System.out.println(view);
        logger.info("End add order");
    }

    public void getAllOrders() {
        logger.debug("Start getAllOrders");
        // Выводим клиентов на экран
        orderService.getAll().forEach(System.out::println);
        logger.info("End getAllOrders");
    }

    public Order getProductById(){
        logger.debug("Start getProductById");
        //Выводим товар по ID
        System.out.println("Введите ID заказа: ");
        logger.info("End getProductById");
        return orderService.getOrderId(sc.nextLine()) ;

    }


}




