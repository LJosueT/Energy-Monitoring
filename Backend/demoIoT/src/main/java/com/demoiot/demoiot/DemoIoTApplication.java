package com.demoiot.demoiot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.demoiot.demoiot.Repository")
public class DemoIoTApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoIoTApplication.class, args);
    }

}
