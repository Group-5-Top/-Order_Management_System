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
    public Product addProduct(String name, Integer price, ProductCategory category) {//ДОБАВИЛ СВОЙ МЕТОД ДОБОВЛЕНИЯ
        // В РЕПОЗИТОРИЙ И СОХРАНЕНИЕ ДЛЯ ПРОВЕРКИ Main Controller
        Product product = new Product(name, price, category);
       return productRepository.saveProduct(product);
    }
    public List<Product> getAll(){//ДОБАВИЛ СВОЙ МЕТОД ПОКАЗА ВСЕХ ПРОДУКТОВ НО ОН
        // НЕ ДОКОНЦА КОРЕКНО РАБОТАЕТ ДЛЯ ПРОВЕРКИ Main Controller
        return productRepository.findAllProducts();
    }
//    public void addProduct(Product product) {//ЗАКОМЕНТИЛ ДЛЯ ПРОВЕРКИ СВОИХ МЕТОДОВ
//        productRepository.saveFile(product);
//    }
//    public List<Product> getProducts() {//ЗАКОМЕНТИЛ ДЛЯ ПРОВЕРКИ СВОИХ МЕТОДОВ
//        return productRepository.findAllProducts();
//    }
    public Product getProductId(int id) throws ProductNotFoundException {
        return productRepository.findByIdProduct(id);
    }
}
