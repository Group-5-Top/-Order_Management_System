package ru.tim_5.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.exeptions.OrderNotFoundException;
import ru.tim_5.models.Order;
import ru.tim_5.services.OrderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final Path filePath;
    private String fileName = "order.txt";

    public OrderRepository() {
        this.filePath = Path.of(fileName);
        try {
            if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    public Path getFilePath() {
        return filePath;
    }

    /**
     * Метод, который непосредственно сохраняет объект Order в файл
     * @param: Order order: Заказ
     * @return Order order: Сохранённый заказ
     */
    public Order saveOrder(Order order) {
        logger.debug("Сохранение заказа");
        try {
            Files.write(filePath, (order + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        logger.info("Заказ сохранен");
        return order;
    }

    /**
     * Метод перезаписывает заказ в файл заказов.
     * @param: Path filePath
     * * @param: int lineNumber
     * * @param: String content
     */
    public void replaceLineInFile(Path filePath, int lineNumber, String content) {
        logger.debug("Перезапись заказа в фаил");
        try {
            // Читаем все строки из файла
            List<String> lines = Files.readAllLines(filePath);
            // Проверяем, что номер строки корректный
            if (lineNumber < 0 || lineNumber > lines.size()) {
                throw new IllegalArgumentException("Номер строки вне диапазона.");
            }
            // Заменяем строку на новую
            lines.set(lineNumber, content);
            // Записываем обновлённые строки обратно в файл
            Files.write(filePath, lines);
            System.out.println("Строка " + (lineNumber + 1) + " успешно заменена.");
        } catch (IOException e) {
            // Обработка исключений при работе с файлами
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Обработка неверного номера строки
            System.err.println(e.getMessage());
        }
        logger.info("Успешная перезапись");
    }

    /**
     * Метод чтения списка заказов из строк файла, с дальнейшим преобразованием их в List<Order>
     * @return List<Order>
     */
    public List<Order> findAllOrder(){
        try {
            return Files.readAllLines(filePath).stream()
                    .map(Order::new )
                    .toList();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод находит покупателя по ID и возвращает его в виде объекта Order
     * @param: String id
     * @return Order
     * @throws: OrderNotFoundException
     */
    public Order findByIdOrder(String id) throws OrderNotFoundException {
        logger.info("Писк заказа по id");
        return  findAllOrder().stream()
                .filter(order -> order.getID().equals(id))
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException("Заказ не найден: " + id));
    }
}
