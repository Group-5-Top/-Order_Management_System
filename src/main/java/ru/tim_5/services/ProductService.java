package ru.tim_5.services;

import ru.tim_5.exeptions.ProductNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.models.ProductCategory;
import ru.tim_5.repositories.ProductRepository;
import ru.tim_5.models.Product;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(String name, Double price, ProductCategory category) {
        Product product = new Product(name, price, category);
        return productRepository.saveProduct(product);
    }

    public List<Product> getAll() {
        // Получаем список продуктов
        return productRepository.findAllProducts();
    }

    public Product getProductId(int id) throws ProductNotFoundException {
        return productRepository.findByIdProduct(id);
    }
}
