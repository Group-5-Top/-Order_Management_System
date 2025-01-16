package ru.tim_5.controllers;


import ru.tim_5.enums.OrderCategory;
import ru.tim_5.models.Customer;
import ru.tim_5.models.Order;
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
    private OrderCategory category;

    CustomerRepository customerRepositories = new CustomerRepository();
    CustomerService customerServices = new CustomerService(customerRepositories);
    CustomerController customerController = new CustomerController(customerServices);

    ProductRepository productRepository = new ProductRepository();
    ProductService productService = new ProductService(productRepository);
    ProductController productController = new ProductController(productService);

    Scanner sc = new Scanner(System.in);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Метод добавления заказов. Передаёт данные с консоли в метод добавления в OrderService
     */
    public void addOrder() {
        System.out.println("Выбрать покупателя по ID");
        Customer customer1 = customerController.getCustomer();
        boolean stop = true;
        List<String> listProductID = new ArrayList<>();
        while (stop) {
            System.out.println("Нажмите 1 для выбора товар по ID или нажмите 0 для завершения: ");
            int start = sc.nextInt();
            switch (start) {
                case 1:
                    System.out.println(listProductID.add(productController.getProductById().getId()));
                    break;
                case 0:
                    stop = false;
                    break;

                default:
                    System.out.println("Такой команды нет!");
            }
        }
        System.out.println("Введи категорию заказа( NEW, PROCESSING, COMPLETED, CANCELLED): ");
        try {
            category = OrderCategory.valueOf(sc.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("\"Ошибка: Введенная категория некорректна." +
                    "Пожалуйста, выберите одну из: NEW, PROCESSING, COMPLETED, CANCELLED.\n");

        }
        String view = orderService.addOrder(customer1.getID(), listProductID, category).toString();
        System.out.println(view);
    }

    /**
     * Метод, выводит все заказы в консоль. Получает List<Order> из OrderService
     */
    public void getAllOrders() {
        System.out.println("Список всех заказов: ");
        List<Order> listOrder = orderService.getListOrder();
        for (int i = 0; i < listOrder.size(); i++) {
            System.out.println("Заказ_№" + (i + 1) + ": " + listOrder.get(i));
        }
    }

    /**
     * Метод получения объекта заказ по его ID.
     * @throws: RuntimeException
     */
    public void getOrderById() throws RuntimeException {
        System.out.println("Введите ID заказа: ");
        System.out.println("Ваш заказ найден. " + "Заказ: " + orderService.getOrderId(sc.nextLine()));
    }

    /**
     * Метод, позволяет делать разные изменения в имеющихся заказах.
     * При одном вызове работает с одним заказом.
     */
    public void changeOrder() {
        boolean exit = true;
        while (exit) {
            try {
                System.out.println("Список всех заказов: ");
                List<Order> listOrder = orderService.getListOrder();
                for (int i = 0; i < listOrder.size(); i++) {
                    System.out.println("Заказ_№" + (i + 1) + ": " + listOrder.get(i));
                }
                System.out.println("Выберите из списка всех заказов тот заказ, в который необходимо внести изменение.");
                int number = sc.nextInt() - 1;
                Order order = listOrder.get(number);
                System.out.println("1. Изменить категорию заказа.");
                System.out.println("0. Назад");

                System.out.println("Выбери опцию:");
                int console = sc.nextInt();
                sc.nextLine();
                switch (console) {
                    case 1 -> {
                        orderService.changeOrderCategory(order.getID(), number);
                        exit = false;
                    }
                    case 0 -> exit = false;
                    default -> System.out.println("Такой команды нет");
                }

            } catch (Exception e) {
                System.out.println("Вы ввели несуществующий номер заказа. Введите номер вашего заказа");
            }
        }
    }
}




