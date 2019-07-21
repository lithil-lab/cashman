package com.bankname.cashdispenser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The spring boot class which aids the application start up
 * 
 * @author Lithil Kuriakose
 *
 */
@SpringBootApplication
public class Application {

    /**
     * The main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
