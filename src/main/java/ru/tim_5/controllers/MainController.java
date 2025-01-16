package ru.tim_5.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.repositories.CustomerRepository;
import ru.tim_5.repositories.OrderRepository;
import ru.tim_5.repositories.ProductRepository;
import ru.tim_5.services.CustomerService;
import ru.tim_5.services.OrderService;
import ru.tim_5.services.ProductService;

import java.util.Scanner;

public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    Scanner sc = new Scanner(System.in);

    OrderRepository orderRepo = new OrderRepository();
    OrderService orderService = new OrderService(orderRepo);
    OrderController orderController = new OrderController(orderService);

    ProductRepository productRepository = new ProductRepository();
    ProductService productService = new ProductService(productRepository);
    ProductController productController = new ProductController(productService);

    CustomerRepository customerRepositories = new CustomerRepository();
    CustomerService customerServices = new CustomerService(customerRepositories);
    CustomerController customerController = new CustomerController(customerServices);

    /**
     * Метод запуска программы и главного меню
     */
    public void start() {
        logger.debug("Start main controller");
        while (true) {
            System.out.print("Программа запущена");
            System.out.println("\n1. Управление товарами");
            System.out.println("2. Управление покупателями");
            System.out.println("3. Управление заказами");
            System.out.println("0. Выйти из программы");

            System.out.println("Выбери опцию:");
            int console = sc.nextInt(); // Ввод цифры для управления
            sc.nextLine();
            try {
                switch (console) {
                    case 1 -> startProduct();
                    case 2 -> startCustomer();
                    case 3 -> startOrder();
                    case 0 -> System.exit(0);
                    default -> System.out.println("Такой команды нет");
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.info("Finish main controller");
        }
    }

    /**
     * Метод вызова меню продуктов
     */
    public void startProduct() {
        logger.debug("Start product controller");
        boolean exit = true;
        while (exit) {
            System.out.println("1. Добавить товар");
            System.out.println("2. Показать все товары");
            System.out.println("3. Найти товар по ID");
            System.out.println("0. Назад");
            System.out.println("Выбери опцию:");

            int console = sc.nextInt();
            sc.nextLine();
            try {
                switch (console) {
                    case 1 -> productController.addProduct();
                    case 2 -> productController.getAllProducts();
                    case 3 -> productController.getProductById();
                    case 0 -> exit = false;
                    default -> System.out.println("Такой команды нет");
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.info("Finish product controller");
        }
    }

    /**
     * Метод вызова меню покупателей
     */
    public void startCustomer() {
        logger.debug("Start customer controller");
        boolean exit = true;
        while (exit) {
            System.out.println("1. Добавить покупателя");
            System.out.println("2. Показать всех покупателей");
            System.out.println("3. Показать покупателя по ID");
            System.out.println("0. Назад");

            System.out.println("Выбери опцию:");
            int console = sc.nextInt();
            sc.nextLine();
            try {
                switch (console) {
                    case 1 -> customerController.addCustomer();
                    case 2 -> customerController.getAllCustomers();
                    case 3 -> customerController.getCustomer();
                    case 0 -> exit = false;
                    default -> System.out.println("Такой команды нет");
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.info("Finish customer controller");
        }
    }

    /**
     * Метод вызова меню заказов
     */
    public void startOrder() {
        logger.debug("Start order controller");
        boolean exit = true;
        while (exit) {
            System.out.println("1. Создать заказ");
            System.out.println("2. Показать все заказы");
            System.out.println("3. Показать заказ по id");
            System.out.println("4. Изменить заказ");
            System.out.println("0. Назад");

            System.out.println("Выбери опцию:");
            int console = sc.nextInt();
            sc.nextLine();
            try {
                switch (console) {
                    case 1 -> orderController.addOrder();
                    case 2 -> orderController.getAllOrders();
                    case 3 -> orderController.getOrderById();
                    case 4 -> orderController.changeOrder();
                    case 0 -> exit = false;
                    default -> System.out.println("Такой команды нет");
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.info("Finish order controller");
        }
    }
}
