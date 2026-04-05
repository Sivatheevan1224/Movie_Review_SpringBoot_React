package com.siva.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Environment Post Processor
 * Loads .env file at Spring Boot startup before other configurations
 * This ensures MongoDB credentials are available when MongodbConfig initializes
 */
public class DotEnvProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> envMap = new HashMap<>();
        
        try {
            // Try loading from classpath first (packaged app)
            try {
                ClassPathResource resource = new ClassPathResource(".env");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                    reader.lines()
                        .filter(line -> !line.trim().isEmpty() && !line.startsWith("#"))
                        .forEach(line -> {
                            String[] parts = line.split("=", 2);
                            if (parts.length == 2) {
                                String key = parts[0].trim();
                                String value = parts[1].trim().replaceAll("^\"|\"$", "");
                                envMap.put(key, value);
                            }
                        });
                }
            } catch (Exception e) {
                // Fall back to file path (development environment)
                String envFilePath = "src/main/resources/.env";
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(envFilePath))))) {
                    reader.lines()
                        .filter(line -> !line.trim().isEmpty() && !line.startsWith("#"))
                        .forEach(line -> {
                            String[] parts = line.split("=", 2);
                            if (parts.length == 2) {
                                String key = parts[0].trim();
                                String value = parts[1].trim().replaceAll("^\"|\"$", "");
                                envMap.put(key, value);
                            }
                        });
                }
            }
            
            // Add environment variables to Spring's property sources
            if (!envMap.isEmpty()) {
                MapPropertySource propertySource = new MapPropertySource("dotenv", envMap);
                environment.getPropertySources().addFirst(propertySource);
            }
        } catch (Exception e) {
            // Silently fail - .env file is optional
        }
    }
}
