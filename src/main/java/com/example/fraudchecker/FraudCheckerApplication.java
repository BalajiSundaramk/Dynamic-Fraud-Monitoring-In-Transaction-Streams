package com.example.fraudchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class FraudCheckerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FraudCheckerApplication.class, args);

        // Auto-open browser after startup
        try {
            Thread.sleep(3000); // wait 3 seconds for server to start
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:8080"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
