package com.lab.xss_sqli_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.lab.xss_sqli_lab.repository")
public class XssSqliLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssSqliLabApplication.class, args);
    }
}
