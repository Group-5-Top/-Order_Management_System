package ru.tim_5.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.exeptions.ProductNotFoundException;
import ru.tim_5.enums.ProductCategory;
import ru.tim_5.repositories.ProductRepository;
import ru.tim_5.models.Product;

import java.util.List;

public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Метод Создаёт объект Product и передаёт его в метод добавления в ProductRepositories
     * @param: String name, Double price, ProductCategory category
     * @param: Double price
     * @param: ProductCategory category
     * @return Product
     */
    public Product addProduct(String name, Double price, ProductCategory category) {
        Product product = new Product(name, price, category);
        return productRepository.saveProduct(product);
    }

    /**
     * Метод получения списка продуктов из ProductRepositories
     * @return List<Product>
     */
    public List<Product> getAll() {
        logger.info("Получение списка всех продуктов");
        return productRepository.findAllProducts();
    }

    /**
     * Метод получения покупателя по ID
     * @param: String id
     * @return Product
     * @throws: ProductNotFoundException
     */
    public Product getProductId(String id) throws ProductNotFoundException {
        logger.info("Получение покупателя по id");
        return productRepository.findByIdProduct(id);
    }
}
