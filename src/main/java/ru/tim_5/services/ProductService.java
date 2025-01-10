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
    public Product addProduct(String name, Double price, ProductCategory category) {
        logger.debug("Start add product");
        Product product = new Product(name, price, category);
        logger.info("End add product");
        return productRepository.saveProduct(product);
    }
    public List<Product> getAll() {
        logger.info("getAll products");
        // Получаем список продуктов
        return productRepository.findAllProducts();
    }

    public Product getProductId(String id) throws ProductNotFoundException {
        logger.info("getProductId product");
        return productRepository.findByIdProduct(id);
    }


}
