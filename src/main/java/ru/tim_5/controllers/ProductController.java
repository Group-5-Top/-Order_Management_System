package ru.tim_5.controllers;

import ru.tim_5.models.CustomerCategory;
import ru.tim_5.models.Product;
import ru.tim_5.models.ProductCategory;
import ru.tim_5.services.ProductService;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    Scanner scanner = new Scanner(System.in);
    private final ProductService productService;
    String nameProduct;
    Integer priceProduct;
    ProductCategory productCategory;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//    public void menu() {//ЗАКОМЕНТИЛ ДЛЯ ПРОВЕРКИ СВОИХ МЕТОДОВ
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("===== Управление товарами =====");
//            System.out.println("1. Добавить товар");
//            System.out.println("2. Показать все товары");
//            System.out.println("0. Назад");
//            System.out.print("Выберите действие: ");
//            int choice = scanner.nextInt();
//            if (choice == 0) break;
//
//            switch (choice) {
//                case 1 -> getProductsAll();
//                case 2 -> getProducts();
//                default -> System.out.println("Некорректный выбор. Попробуйте еще раз.");
//            }
//        }
//    }

    public void addP() {//ДОБАВИЛ СВОЙ МЕТОД ДОБОВЛЕНИЯ ДЛЯ ПРОВЕРКИ Main Controller
        System.out.println("Введи названия товара: ");
        nameProduct = scanner.next();
        System.out.println("Введи цену товара: ");
        priceProduct = scanner.nextInt();
        System.out.println("Выберите категорию (FOOD, ELECTRONICS, CLOTHING, FURNITURE): ");
        productCategory = ProductCategory.valueOf(scanner.next());
        String view = String.valueOf(productService.addProduct(nameProduct, priceProduct, productCategory));
        System.out.println(view);
    }
    public void getAllProducts() {//ДОБАВИЛ СВОЙ МЕТОД ПОКАЗА СПИСКА ВСЕХ ПРОДУКТОВ ДЛЯ ПРОВЕРКИ Main Controller
        String view = productService.getAll().toString();
        System.out.println(view);
    }
//    public void getProductsAll() {//ЗАКОМЕНТИЛ ДЛЯ ПРОВЕРКИ СВОИХ МЕТОДОВ
//        List<Product> products = productService.addProduct();
//        products.forEach(System.out::println);
//    }
}
