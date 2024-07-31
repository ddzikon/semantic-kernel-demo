package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.example.ai", "org.example.model", "org.example.view", "org.example.viewmodel"})
public class Main {
    public static void main(String[] args) {
        Launcher.launch(args);
    }
}
