package ru.tim_5.models;

import ru.tim_5.enums.CustomerCategory;

import java.util.Objects;

public class Customer {

    private final String ID;
    private String name;
    private CustomerCategory category;

    public Customer() {
        this.ID = IdGenerator.generateID();
    }

    public Customer(String name, CustomerCategory category) {
        this.ID = IdGenerator.generateID();
        this.name = name;
        this.category = category;
    }


    /**
     * Конструктор копирования
     * @param other: Customer other
     */
    public Customer(Customer other) {
        ID = other.ID;
        this.name = other.name;
        this.category = other.category;
    }

    /**
     * Конструктор Builder
     * @param other: BuilderCustomer other
     */
    public Customer(BuilderCustomer other) {
        ID = IdGenerator.generateID();
        this.name = other.name;
        this.category = other.category;
    }

    @Override
    public String toString() {
        return
                ID + ", " + name + ", " + category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(CustomerCategory category) {
        this.category = category;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public CustomerCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(ID, customer.ID) && Objects.equals(name, customer.name) && category == customer.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, category);
    }

    /**
     * Метод-конструктор, который получает строку из файла и преобразует её в объект Customer.
     * @param s: String s
     */
    public Customer(String s){
        String[] str = s.split(", ");
        this.ID = str[0];
        this.name = str[1];
        this.category = CustomerCategory.valueOf(str[2]);
    }

    public static BuilderCustomer builderCustomer() {
        return new BuilderCustomer();
    }

    public static class BuilderCustomer {
        private String name;
        private CustomerCategory category;

        public BuilderCustomer name(String name) {
            this.name = name;
            return this;
        }

        public BuilderCustomer category(CustomerCategory category) {
            this.category = category;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
