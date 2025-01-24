package ru.tim_5.test;

import org.junit.Test;
import ru.tim_5.models.Product;
import ru.tim_5.repositories.ProductRepository;
import ru.tim_5.services.ProductService;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class productTestClass {
    private ProductService productService;
private static final String testFile = "product.txt";


    @Before
    public void setUp() { ProductRepository productRepository = new ProductRepository() {
        @Override
        public void save(Product product) {
            try (FileWriter writer = new FileWriter(testFile, true)) {
                String productData = product.getId() + ";" +
                        product.getName() + ";" +
                        product.getPrice() + ";" +
                        product.getCategory().name() + System.lineSeparator();
                writer.write(productData);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при сохранении продукта: " + e.getMessage());
            }
        }
        @Override
        public List<Product> loadAll() {
            return super.findAllProducts();
        }

        @Override
        public Product findById(int id) {
            return null;
        }

        @Override
        public Product findById(String id) {
            return null;
        }

    };
        productService = new ProductService(productRepository);
        clearTestFile();
    }
    @After
    public void tearDown() {
        deleteTestFile();
    }
    private void clearTestFile() {
        try (FileWriter writer = new FileWriter(testFile, false)) {
            writer.write("");
        } catch (IOException e) {
            fail("Не удалось очистить тестовый файл: " + e.getMessage());
        }
    }
    private void deleteTestFile() {
        File file = new File(testFile);
        if (file.exists()) {
            file.delete();
        }
    }
    @Test
    public void testGetAllProducts() {List<Product> foundProducts = productService.getAll();

        assertEquals(4, foundProducts.size());
        assertEquals("Тест1", foundProducts.get(0).getName());
        assertEquals("Тест2", foundProducts.get(1).getName());
        assertEquals("Тест3", foundProducts.get(2).getName());
        assertEquals("Тест4", foundProducts.get(3).getName());
    }
}