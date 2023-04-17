package com.back.balbadak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BalbadakApplication {

    public static void main(String[] args) {
        SpringApplication.run(BalbadakApplication.class, args);
    }

}
