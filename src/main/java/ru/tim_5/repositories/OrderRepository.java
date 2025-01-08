package ru.tim_5.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OrderRepository {
    private final Path filePath;
    private String fileName = "orders.txt ";

    public OrderRepository() {
        this.filePath = Path.of(fileName);
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }





}
