package com.blibli.belajar.design.pattern.protype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class PrototypeApplication {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Customer {
        private String id;
        private String name;
        private String category;
        private Integer discount;
    }

    @SpringBootApplication
    public static class Configuration {

        // kalau mw custom nama bean
        @Bean("standardCustomer")
        @Scope("prototype")
        public Customer standardCustomer() {
            return Customer.builder().category("STANDARD").discount(10).build();
        }

        @Bean
        @Scope("prototype")
        public Customer premiumCustomer() {
            return Customer.builder().category("PREMIUM").discount(50).build();
        }
    }

    public static void main(String[] args) {
//        Customer customerStandard1 = Customer.builder().id("1").name("Eko").category("STANDARD").discount(10).build();
//        Customer customerStandard2 = Customer.builder().id("1").name("Eko").category(customerStandard1.getCategory()).discount(customerStandard1.getDiscount()).build();
//        Customer customerStandard3 = Customer.builder().id("1").name("Eko").category(customerStandard1.getCategory()).discount(customerStandard1.getDiscount()).build();
//        Customer customerStandard4 = Customer.builder().id("1").name("Eko").category(customerStandard1.getCategory()).discount(customerStandard1.getDiscount()).build();
//        Customer customerStandard5 = new Customer();
//        BeanUtils.copyProperties(customerStandard1, customerStandard5); // tapi harus tau lokasi kedua posisi / lokasi object

        ApplicationContext context = SpringApplication.run(Configuration.class);
        Customer customerStandard1 = context.getBean("standardCustomer", Customer.class);
        Customer customerStandard2 = context.getBean("standardCustomer", Customer.class);
        Customer customerStandard3 = context.getBean("standardCustomer", Customer.class);

        Customer customerPremium1 = context.getBean("premiumCustomer", Customer.class);
        Customer customerPremium2 = context.getBean("premiumCustomer", Customer.class);
        Customer customerPremium3 = context.getBean("premiumCustomer", Customer.class);

        System.out.println(customerStandard1 == customerStandard2);
        System.out.println(customerStandard1);
        System.out.println(customerStandard2);
        System.out.println(customerStandard3);

        System.out.println(customerPremium1);
        System.out.println(customerPremium2);
        System.out.println(customerPremium3);
    }
}
