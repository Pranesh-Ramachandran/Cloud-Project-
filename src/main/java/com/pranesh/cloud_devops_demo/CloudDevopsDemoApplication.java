package com.pranesh.cloud_devops_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class CloudDevopsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDevopsDemoApplication.class, args);
    }

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
            "application", "Cloud DevOps Demo",
            "status", "running",
            "message", "Hello from Pranesh's cloud project!"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
