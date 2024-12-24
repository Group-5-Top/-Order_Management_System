package ru.tim_5.models;

// <<<<<<< product
import java.util.Objects;

public class Product {
    private final int id;
    private String name;
    private double price;
    private ProductCategory category;

    public Product(int id, String name, double price, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product produck1 = (Product) o;
        return id == produck1.id && Double.compare(price, produck1.price) == 0 && Objects.equals(name, produck1.name) && category == produck1.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category);
    }

    @Override
    public String toString() {
        return id + name + '\'' + price + category;
    }
=======
public class Product {
// >>>>>>> master
}
