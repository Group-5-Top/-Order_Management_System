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
    Double priceProduct;
    ProductCategory productCategory;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void addP() {
        //ДОБАВИЛ СВОЙ МЕТОД ДОБОВЛЕНИЯ ДЛЯ ПРОВЕРКИ Main Controller
        System.out.println("Введи названия товара: ");
        nameProduct = scanner.next();
        System.out.println("Введи цену товара: ");
        priceProduct = scanner.nextDouble();
        System.out.println("Выберите категорию (FOOD, ELECTRONICS, CLOTHING): ");
        try {
            productCategory = ProductCategory.valueOf(scanner.next());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Введенная категория некорректна. " +
                    "Пожалуйста, выберите одну из: FOOD, ELECTRONICS, CLOTHING.");
        }

        String view = productService.addProduct(nameProduct, priceProduct, productCategory).toString();
        System.out.println(view);
    }
    public void getAllProducts() {
        // Выводим товары на экран
        productService.getAll().forEach((el)-> System.out.println("Товар: {" + el + "}"));
    }
}
