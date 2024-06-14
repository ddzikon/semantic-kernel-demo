package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = { "agent", "app" })
@ComponentScan(basePackages = { "org.example.agent", "org.example.app" })
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}