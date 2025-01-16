package ru.tim_5.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.controllers.ProductController;
import ru.tim_5.exeptions.ProductNotFoundException;
import ru.tim_5.models.Product;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ProductRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private final Path filePath;
    private String fileName = "product.txt";

    public ProductRepository() {
        this.filePath = Path.of(fileName);
        try {
            if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    /**
     * Метод, который непосредственно сохраняет объект Product в файл
     * @param product: Product
     * @return Product
     */
    public Product saveProduct(Product product) {
        logger.debug("Saving product");
        //Метод сохраняет продукт в файл
        try {
            Files.write(filePath, (product + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        logger.debug("Product saved");
        return product;
    }

    /**
     * Метод чтения списка товаров из строк файла, с дальнейшим преобразованием их в List<Product>
     * @return List<Product>
     */
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

    /**
     * Метод находит товар по ID и возвращает его в виде объекта Product
     * @param id String id
     * @return Product
     * @throws: ProductNotFoundException
     */
    public Product findByIdProduct(String id) throws ProductNotFoundException {
        return findAllProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Продукт с таким ID не найден: " + id));
    }
}