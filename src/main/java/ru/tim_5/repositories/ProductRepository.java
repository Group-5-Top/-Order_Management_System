package ru.tim_5.repositories;

// <<<<<<< product

import ru.tim_5.exeptions.ProductNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.models.Product;
import ru.tim_5.models.ProductCategory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {//ПОДРЕДАЧИЛ
//    private final String filePath = "products.txt";
    private final Path filePath;//ДОБАВИЛ СВОИ ПОЛЯ
    private String fileName = "product.txt";//ДОБАВИЛ СВОИ ПОЛЯ

//    public void saveFile(Product product) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
//            writer.write(product.getId() + ";" + product.getName() + ";" + product.getPrice() + ";" + product.getCategory());
//            writer.newLine(); // защита от дурака , что бы не писала в 1 строку
//            writer.close(); // Что бы завершала метод и не срало в память
//        } catch (IOException e) {
//            e.printStackTrace(); // поиск по трасировке
//        }
//    }

    public ProductRepository() {//ДОБАВИЛ СВОЙ МЕТОД
        this.filePath = Path.of(fileName);
//        countId = 0;
        try {
            if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public Product saveProduct(Product product) {//ДОБАВИЛ СВОЙ МЕТОД СОХРАНЕНИЯ В ФАЙЛЫ
        // ДЛЯ ПРОВЕРКИ Main Controller
//        product.setId(IdGenerator.generateID());
//        IdGenerator.generateID();
        try {
            Files.write(filePath, (product + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return product;
    }
    public List<Product> findAllProducts() {//ДОБАВИЛ СВОЙ МЕТОД ПОКАЗА ВСЕХ ПРОДУКТОВ НО ОН
        // НЕ ДОКОНЦА КОРЕКНО РАБОТАЕТ ДЛЯ ПРОВЕРКИ Main Controller
        try {
            return Files.readAllLines(filePath).stream()
                    .map(l -> new Product(l))
                    .toList();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

//    public List<Product> findAll() {//ЗАКОМЕНТИЛ ДЛЯ ПРОВЕРКИ СВОИХ МЕТОДОВ
//        List<Product> products = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(";");
//                int id = Integer.parseInt(parts[0]);
//                String name = parts[1];
//                double price = Double.parseDouble(parts[2]);
//                ProductCategory category = ProductCategory.valueOf(parts[3]);
//                products.add(new Product(name, price, category));
//            }
//        } catch (IOException e) {
//            e.printStackTrace(); // трасировка
//        }
//        return products;
//    }

    public Product findByIdProduct(int id) {//ПОДРЕДАЧИЛ НАДО ПРОВЕРЯТЬ И ТЕСТИТЬ
        return findAllProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

    }
}