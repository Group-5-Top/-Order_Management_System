package ru.tim_5.models;

import java.util.Objects;

public class Product {
    private final String ID;
    private String name;
    private double price;
    private ProductCategory category;

    public Product() {
        this.ID = IdGenerator.generateID(); // Уникальное ID генерируется при создании объекта Product
    }

    public Product(String name, double price, ProductCategory category) {
        this.ID = IdGenerator.generateID(); // Уникальное ID генерируется при создании объекта Product
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product(Product other) {
        ID = other.ID; // Уникальное ID копируется с имеющегося экземпляра
        this.name = other.name;
        this.price = other.price;
        this.category = other.category;
    }

    public Product(BuilderProduct other) {
        ID = IdGenerator.generateID(); // Уникальное ID генерируется при создании объекта Product
        this.name = other.name;
        this.price = other.price;
        this.category = other.category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getId() {
        return ID;
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
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && Objects.equals(ID, product.ID) && Objects.equals(name, product.name) && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, price, category);
    }

    @Override
    public String toString() {
        return ID + ", " + name + ", " + price + ", " + category;
    }

    public Product(String s){//ДОБАВИЛ СВОЙ МЕТОД ДЛЯ ПРЕОБРОЗОВАНИЯ ФАИЛА
        String[] str = s.split(", ");
        this.ID = str[0];
        this.name = str[1];
        this.price = Double.parseDouble(str[2]);
        this.category = ProductCategory.valueOf(str[3]);
    }
    public static BuilderProduct builderProduct() {
        return new BuilderProduct();
    }

    public static class BuilderProduct {
        private String name;
        private double price;
        private ProductCategory category;

        public BuilderProduct name(String name) {
            this.name = name;
            return this;
        }

        public BuilderProduct price(Double price) {
            this.price = price;
            return this;
        }

        public BuilderProduct category(ProductCategory category) {
            this.category = category;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

}
