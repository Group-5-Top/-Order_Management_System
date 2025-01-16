package ru.tim_5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tim_5.controllers.MainController;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.debug("Маин старт");
        MainController controller = new MainController();
        controller.start();
        logger.info("Маин финиш");
    }
}





