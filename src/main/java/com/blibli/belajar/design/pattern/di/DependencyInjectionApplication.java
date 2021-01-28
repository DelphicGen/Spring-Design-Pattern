package com.blibli.belajar.design.pattern.di;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class DependencyInjectionApplication {

    @SpringBootApplication
    public static class Application {

//        @Bean
//        public Foo foo() {
//            return new Foo();
//        }

        @Component
//        @Scope("Protoype")
        public static class Foo {
        }

        @Component
        @Data
        public static class Bar {
            @Autowired // ksh tau spring ini harus diinject
            private Foo foo;

        }

    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);

        Application.Foo foo = context.getBean(Application.Foo.class);
        Application.Bar bar = context.getBean(Application.Bar.class);

        System.out.println(foo == bar.getFoo());
    }

}
