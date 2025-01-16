package ru.tim_5.models;

import ru.tim_5.enums.OrderCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private final String ID;
    private String customerID;
    private List<String> productID;
    private OrderCategory category;


    public Order() {
        this.ID = "З:" + IdGenerator.generateID();
    }

    public Order(String customerID, List<String> productID, OrderCategory category) {
        this.ID = "З:" + IdGenerator.generateID();
        this.customerID = customerID;
        this.productID = productID;
        this.category = category;
    }

    /**
     * Конструктор копирования
     * @param other: Order other
     */
    public Order(Order other) {
        this.ID = "З:" + IdGenerator.generateID();
        this.customerID = other.customerID;
        this.productID = other.productID;
        this.category = other.category;
    }

    /**
     * Конструктор Builder
     * @param other: BuilderOrder other
     */
    public Order(BuilderOrder other) {
        this.ID = "З:" + IdGenerator.generateID();
        this.customerID = other.customerID;
        this.productID = other.productID;
        this.category = other.category;
    }

    public void setCustomer(String customerID) {
        this.customerID = customerID;
    }

    public void setProduct(List<String> productID) {
        this.productID = productID;
    }

    public void setCategory(OrderCategory category) {
        this.category = category;
    }

    public String getID() {
        return ID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public List<String> getProductID() {
        return productID;
    }

    public OrderCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return  ID  +"; " + customerID + "; " + productID + "; " + category;
    }

    /**
     * Метод-конструктор, который получает строку из файла и преобразует её в объект Order.
     * @param s: String s
     */
    public Order(String s){
        String[] str = s.split("; ");
        this.ID = str[0];
        this.customerID = str[1];
        String ks = str[2].replace("[", "").replace("]", "");
        String[] str2 = ks.split(", ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < str2.length; i++) {
            list.add(str2[i]);
        }
        this.productID = list;
        this.category = OrderCategory.valueOf(str[3]);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(ID, order.ID) && Objects.equals(customerID, order.customerID) && Objects.equals(productID, order.productID) && category == order.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, customerID, productID, category);
    }

    public static BuilderOrder builderOrder() {
        return new BuilderOrder();
    }

    public static class BuilderOrder {
        private String customerID;
        private List<String > productID;
        private OrderCategory category;

        public BuilderOrder customer(String customerID) {
            this.customerID = customerID;
            return this;
        }

        public BuilderOrder product(List<String> productID) {
            this.productID = productID;
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
