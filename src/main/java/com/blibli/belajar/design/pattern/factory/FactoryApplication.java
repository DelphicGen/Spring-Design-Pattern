package com.blibli.belajar.design.pattern.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class FactoryApplication {

    @Data
    @AllArgsConstructor
    public static class Database {

        private String host;
        private Integer port;
        private String username;
        private String password;
    }

    // pas di comple <Database> jadi <Object>. saat dicompile informasi itu hilang jd spring hrs tau datanya apa
    public static class DatabaseFactory implements FactoryBean<Database> {

        @Override
        public Database getObject() throws Exception {
            Database database = new Database("localhost", 1000, "admin", "admin");
            return database;
        }

        @Override
        public Class<?> getObjectType() {
            return null;
        }
    }

    @SpringBootApplication
    public static class Configuration {

//        @Bean // harus masukkin byk param
//        public Database database() {
//            Database database = new Database();
//            return database;
//        }

        @Bean
        public FactoryBean<Database> database() {
            return new DatabaseFactory();
        }

        public static void main(String[] args) {
            ApplicationContext context = SpringApplication.run(Configuration.class);
            Database database = context.getBean(Database.class);
        }

    }
}
