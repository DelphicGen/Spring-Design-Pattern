package com.blibli.belajar.design.pattern.adaptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

public class AdaptorPattern {
//    Adapatornya adalah health indicator
    public static class MyHealthIndicator implements HealthIndicator {

        @Override
        public Health health() {
            return Health.up().build();
        }
    }

    public static class MyHealthIndicator2 implements HealthIndicator {

        @Override
        public Health health() {
            return Health.down().build();
        }
    }

    @SpringBootApplication
    public static class Application {
        @Bean
        public MyHealthIndicator myHealthIndicator() {
            return new MyHealthIndicator();
        }

        @Bean
        public MyHealthIndicator2 myHealthIndicator2() {
            return new MyHealthIndicator2();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
