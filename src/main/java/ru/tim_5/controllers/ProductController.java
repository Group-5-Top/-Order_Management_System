package ru.tim_5.controllers;

import ru.tim_5.enums.ProductCategory;
import ru.tim_5.models.Product;
import ru.tim_5.services.ProductService;

import java.util.Scanner;

public class ProductController {
    private final ProductService productService;

    Scanner sc = new Scanner(System.in);
    String name;
    Double price;
    ProductCategory category;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(){
        System.out.println("Введи название товара: ");
        name = sc.next();
        System.out.println("Введите цену товара: ");
        price = sc.nextDouble();
        System.out.println("Введи категорию товара(FOOD, ELECTRONICS, CLOTHING): ");
        try {
            category = ProductCategory.valueOf(sc.next());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Введенная категория некорректна. " +
                    "Пожалуйста, выберите одну из: FOOD, ELECTRONICS, CLOTHING.");
        }

        String view = productService.addProduct(name, price, category).toString();
        System.out.println(view);
    }

    public void getAllProducts(){
        // Выводим товары на экран
        productService.getAll().forEach((el)-> System.out.println("Товар: {" + el + "}"));
    }

    public Product getProductById(){
        //Выводим товар по ID
        System.out.println("Введите ID товара: ");
        System.out.println(productService.getProductId(sc.nextLine()));
        return productService.getProductId(sc.nextLine());
    }


}
