package ru.tim_5.repositories;

// <<<<<<< product
import ru.tim_5.Exceptions.ProductNotFoundException;
import ru.tim_5.models.Product;
import ru.tim_5.models.ProductCategory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final String filePath = "products.txt";

    public void saveFile(Product product) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(product.getId() + ";" + product.getName() + ";" + product.getPrice() + ";" + product.getCategory());
            writer.newLine(); // защита от дурака , что бы не писала в 1 строку
            writer.close(); // Что бы завершала метод и не срало в память
        } catch (IOException e) {
            e.printStackTrace(); // поиск по трасировке
        }
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                ProductCategory category = ProductCategory.valueOf(parts[3]);
                products.add(new Product(name, price, category));
            }
        } catch (IOException e) {
            e.printStackTrace(); // трасировка
        }
        return products;
    }

    public Product findById(int id) {
        return findAll().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

    }
}