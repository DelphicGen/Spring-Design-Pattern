package com.blibli.belajar.design.pattern.dynamicmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

public class DynamicModule {
    @SpringBootApplication
    public static class Application {

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
