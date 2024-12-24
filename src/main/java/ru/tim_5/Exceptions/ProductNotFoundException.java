package ru.tim_5.Exceptions;

public class  ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException (String message){
        super (message);
    }
}
