package ru.tim_5.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.enums.ProductCategory;
import ru.tim_5.models.Product;
import ru.tim_5.services.ProductService;

import java.util.Scanner;

public class ProductController {

    private final ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    Scanner sc = new Scanner(System.in);
    String name;
    Double price;
    ProductCategory category;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(){
        logger.debug("Start add product");
        System.out.println("Введи название товара: ");
        name = sc.next();
        System.out.println("Введите цену товара: ");
        price = sc.nextDouble();
        System.out.println("Введи категорию товара(FOOD, ELECTRONICS, CLOTHING): ");
        try {
            category = ProductCategory.valueOf(sc.next());
        } catch (IllegalArgumentException e) {
           logger.error("Ошибка: Введенная категория некорректна. " +
                   "Пожалуйста, выберите одну из: FOOD, ELECTRONICS, CLOTHING.");
        }

        String view = productService.addProduct(name, price, category).toString();
        System.out.println(view);
        logger.info("End add product");
    }

    public void getAllProducts(){
        logger.debug("Start get all products");
        // Выводим товары на экран
        productService.getAll().forEach((el)-> System.out.println("Товар: {" + el + "}"));
        logger.info("End get all products");
    }

    public Product getProductById(){
        logger.debug("Start get product by id");
        //Выводим товар по ID
        System.out.println("Введите ID товара: ");
        logger.info("End get product by id");
        return productService.getProductId(sc.nextLine());
    }
}
