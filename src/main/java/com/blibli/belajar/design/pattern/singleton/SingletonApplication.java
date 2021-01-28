package com.blibli.belajar.design.pattern.singleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class SingletonApplication {
    @SpringBootApplication
    public static class Configuration {
        @Bean
        public Contoh contoh() {
            return new Contoh();
        }
    }

    public static class Contoh {

    }
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SingletonApplication.Configuration.class);
        Contoh contoh1 = context.getBean(SingletonApplication.Contoh.class);
        Contoh contoh2 = context.getBean(SingletonApplication.Contoh.class);

//        Kalau bikin objectnya manual (new Contoh), pasti false karena dua object yg berbeda
        System.out.println(contoh1 == contoh2);
    }
}
