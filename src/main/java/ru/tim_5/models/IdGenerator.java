package ru.tim_5.models;

import java.util.UUID;

public class IdGenerator {
    // Метод для генерации случайного ID
    public static String generateID(){
        // Получаем текущее время в миллисекундах
        /*long timestamp = System.currentTimeMillis();*/

        // Генерируем случайный UUID

        // Форматируем уникальные идентификаторы как "<timestamp>-<uniquePart>"
        return UUID.randomUUID().toString();


    }
}
