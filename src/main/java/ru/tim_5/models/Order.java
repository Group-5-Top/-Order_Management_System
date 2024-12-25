package ru.tim_5.models;

import java.util.Objects;

public class Order {
    private final String ID;
    private Customer customer;
    private Product product;
    private OrderCategory category;

    public Order() {
        this.ID = IdGenerator.generateID(); // Уникальное ID генерируется при создании объекта Order
    }

    public Order(Customer customer, Product product, OrderCategory category) {
        this.ID = IdGenerator.generateID(); // Уникальное ID генерируется при создании объекта Product
        this.customer = customer;
        this.product = product;
        this.category = category;
    }

    public Order(Order other) {
        this.ID = IdGenerator.generateID(); // Уникальное ID генерируется при создании объекта Product
        this.customer = other.customer;
        this.product = other.product;
        this.category = other.category;
    }

    public Order(BuilderOrder other) {
        this.ID = IdGenerator.generateID(); // Уникальное ID генерируется при создании объекта Product
        this.customer = other.customer;
        this.product = other.product;
        this.category = other.category;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCategory(OrderCategory category) {
        this.category = category;
    }

    public String getID() {
        return ID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public OrderCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID='" + ID + '\'' +
                ", Покупатель=" + customer +
                ", Товар=" + product +
                ", Статус заказа=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(ID, order.ID) && Objects.equals(customer, order.customer) && Objects.equals(product, order.product) && category == order.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, customer, product, category);
    }

    public static BuilderOrder builderOrder() {
        return new BuilderOrder();
    }

    public static class BuilderOrder {
        private Customer customer;
        private Product product;
        private OrderCategory category;

        public BuilderOrder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public BuilderOrder product(Product product) {
            this.product = product;
            return this;
        }

        public BuilderOrder category(OrderCategory category) {
            this.category = category;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}
