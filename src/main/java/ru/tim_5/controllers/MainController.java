package ru.tim_5.controllers;

import ru.tim_5.repositories.CuctomerRepository;
import ru.tim_5.services.CustomerService;

import java.util.Scanner;

public class MainController {

    Scanner sc = new Scanner(System.in);


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
            int chonsol = sc.nextInt();
            sc.nextLine();
            try {
                switch (chonsol){
//                  case 1 -> startProduct();
                    case 2 -> startCustomer();
//                  case 3 ->;
                    case 0 -> System.exit(0);
                    default -> System.out.println("Такой команды нет");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
// new
    public void startCustomer() {
        boolean exit = true;
        while (exit) {
            System.out.println("1. Добавить покупателя");
            System.out.println("2. Показать всех покупателей");
            System.out.println("0. Назад");

            System.out.println("Выбери опцию:");
            int chonsol = sc.nextInt();
            sc.nextLine();
            try {
                switch (chonsol){
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
}
