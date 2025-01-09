package ru.tim_5.repositories;

// <<<<<<< product

import ru.tim_5.exeptions.ProductNotFoundException;
import ru.tim_5.models.Product;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ProductRepository {

    private final Path filePath;
    private String fileName = "product.txt";

    public ProductRepository() {
        this.filePath = Path.of(fileName);
        try {
            if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }



    public Product saveProduct(Product product) {
        //Метод сохраняет продукт в файл
        try {
            Files.write(filePath, (product + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return product;
    }

    public List<Product> findAllProducts() {
        //Метод получения листа продуктов из файла
        try {
            return Files.readAllLines(filePath).stream()
                    .map(Product::new)
                    .toList();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Product findByIdProduct(String id) {
        //ПОДРЕДАЧИЛ НАДО ПРОВЕРЯТЬ И ТЕСТИТЬ
        return findAllProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Продукт с таким ID не найден: " + id));

    }
}