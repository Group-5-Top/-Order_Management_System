package ru.tim_5.Controllers;

import ru.tim_5.models.Product;
import ru.tim_5.models.ProductCategory;
import ru.tim_5.Services.ProductService;
import ru.tim_5.repositories.ProductRepository;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Управление товарами =====");
            System.out.println("1. Добавить товар");
            System.out.println("2. Показать все товары");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1 -> getProductsAll();
                case 2 -> getProducts();
                default -> System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }
    public void getProductsAll() {
        List<Product> products = productService.addProduct();
        products.forEach(System.out::println);
    }
}
