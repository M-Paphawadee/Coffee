package com.coffeeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class CoffeelineApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(CoffeelineApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CoffeelineApplication.class, args);
        
        logger.info("============================================");
        logger.info("☕ COFFEELINE Application Started!");
        logger.info("เปิด Chrome: http://localhost:8080");
        logger.info("============================================");
    }
}