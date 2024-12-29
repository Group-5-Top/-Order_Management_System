package ru.tim_5.controllers;

import ru.tim_5.repositories.CuctomerRepository;
import ru.tim_5.repositories.OrderRepository;
import ru.tim_5.repositories.ProductRepository;
import ru.tim_5.services.CustomerService;
import ru.tim_5.services.OrderService;
import ru.tim_5.services.ProductService;

import java.util.Scanner;

public class MainController {

    Scanner sc = new Scanner(System.in);

//    OrderRepository orderRepo = new OrderRepository();//ДОБАВИЛ
//    OrderService orderService = new OrderService(orderRepo);//ДОБАВИЛ
//    OrderController orderController = new OrderController(orderService);//ДОБАВИЛ

//ДОБАВИЛ
    ProductRepository productRepository= new ProductRepository();
    ProductService productService = new ProductService(productRepository);
    ProductController productController = new ProductController(productService);

    CuctomerRepository customerRepositories = new CuctomerRepository();
    CustomerService customerServices = new CustomerService(customerRepositories);
    CustomerController customerController = new CustomerController(customerServices);

    public void start(){
        while(true){
            System.out.print("Программа запущена");
            System.out.println("\n1. Управление товарами");
            System.out.println("2. Управление покупателями");
            System.out.println("3. Управление заказами");
            System.out.println("0. Выйт");

            System.out.println("Выбери опцию:");
            int console = sc.nextInt();
            sc.nextLine();
            try {
                switch (console){
                  case 1 -> startProduct();
                  case 2 -> startCustomer();
                  case 3 -> startOrder();
                  case 0 -> System.exit(0);
                  default -> System.out.println("Такой команды нет");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void startProduct(){//ДОБАВИЛ
        boolean exit = true;
        while(exit){
            System.out.println("1. Добавить товар");
            System.out.println("2. Показать все товары");
            System.out.println("0. Назад");
            System.out.println("Выбери опцию:");

            int console = sc.nextInt();
            sc.nextLine();
            try {
                switch (console){
                    case 1 -> productController.addP(); //метод добовления товара
                    case 2 -> productController.getAllProducts(); // метот показа фсех товаров
                    case 0 -> exit = false;
                    default -> System.out.println("Такой команды нет");
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void startCustomer() {
        boolean exit = true;
        while (exit) {
            System.out.println("1. Добавить покупателя");
            System.out.println("2. Показать всех покупателей");
            System.out.println("0. Назад");

            System.out.println("Выбери опцию:");
            int console = sc.nextInt();
            sc.nextLine();
            try {
                switch (console){
                    case 1 -> customerController.addCustomer();
                    case 2 -> customerController.getAllCustomers();
                    case 0 -> exit = false;
                    default -> System.out.println("Такой команды нет");
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void startOrder() {//ДОБАВИЛ
        boolean exit = true;
        while (exit) {
            System.out.println("1. Создать заказ");
            System.out.println("2. Показать все заказы");
            System.out.println("0. Назад");

            System.out.println("Выбери опцию:");
            int console = sc.nextInt();
            sc.nextLine();
            try {
                switch (console){
//                    case 1 -> //В БУДУЮЩЕМ СОЗДАНИЕ ЗАКАЗОВ
//                    case 2 -> //В БУДУЮЩЕМ ПОКАЗ ВСЕХ ЗАКАЗОВ
//                    case 3 -> //В БУДУЮЩЕМ ИЗМЕНИТЬ СТАТУС ЗАКАЗА
                    case 0 -> exit = false;
                    default -> System.out.println("Такой команды нет");
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
