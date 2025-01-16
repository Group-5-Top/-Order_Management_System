package ru.tim_5.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.exeptions.CustomerNotFoundException;
import ru.tim_5.models.Customer;
import ru.tim_5.services.OrderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CustomerRepository {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
    private final Path filePath;
    private String fileName = "customer.txt";

    public CustomerRepository() {
        this.filePath = Path.of(fileName);
        try {
            if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }

        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    /**
     * Метод, который непосредственно сохраняет объект Customer в файл
     * @param customer: Customer.
     * @return Customer
     */
    public Customer saveCustomers(Customer customer) {
        logger.debug("Saving customer information");
        try {
            Files.write(filePath, (customer + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        logger.info("New customer information saved");
        return customer;
    }

    /**
     * Метод чтения списка покупателей из строк файла, с дальнейшим преобразованием их в List<Customer>
     * @return List<Customer>
     */
    public List<Customer> findAllCustomer(){
        try {
            return Files.readAllLines(filePath).stream()
                    .map(Customer::new)
                    .toList();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод находит покупателя по ID и возвращает его в виде объекта Customer
     * @param id String id
     * @return Customer
     * @throws: CustomerNotFoundException
     */
    public Customer findByIdCustomer(String id) throws CustomerNotFoundException {
        return  findAllCustomer().stream()
                .filter(customer -> customer.getID().equals(id))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Пользователь не найден: " + id));
    }
}
