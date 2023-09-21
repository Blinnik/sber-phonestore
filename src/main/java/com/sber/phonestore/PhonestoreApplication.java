package com.sber.phonestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * An application consisting of a Phone entity with the ability to perform CRUD operations on it
 *
 * @author Vladimir Blinnikov - "blinnik2001@yandex.ru"
 */
@SpringBootApplication
public class PhonestoreApplication {

    /**
     * Launches the application
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PhonestoreApplication.class, args);
    }

}
