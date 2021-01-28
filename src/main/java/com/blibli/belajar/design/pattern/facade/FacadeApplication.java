package com.blibli.belajar.design.pattern.facade;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class FacadeApplication {

    public static interface AddressService {

        void save(String customerId, String address, String city);
        void remove(String customerId, String address, String city);
        void update(String customerId, String address, String city);

    }

    public static class AddressController {
        @Setter
//        @Autowired // nanti spring cari bean addressservice trs diinject kesini
        private AddressService addressService;

        public void saveAddress(String customerId, String address, String city) {
            System.out.println("Contoller");
            addressService.save(customerId, address, city);
        }
    }

    public static class AddressServiceImplPostgre implements AddressService {

        @Override
        public void save(String customerId, String address, String city) {
            System.out.println("Complicated implementation Postgre");
        }

        @Override
        public void remove(String customerId, String address, String city) {
            System.out.println("Complicated implementation Postgre");
        }

        @Override
        public void update(String customerId, String address, String city) {
            System.out.println("Complicated implementation Postgre");
        }
    }

    public static class AddressServiceImplMongo implements AddressService {

        @Override
        public void save(String customerId, String address, String city) {
            System.out.println("Complicated implementation Mongo");
        }

        @Override
        public void remove(String customerId, String address, String city) {
            System.out.println("Complicated implementation Mongo");
        }

        @Override
        public void update(String customerId, String address, String city) {
            System.out.println("Complicated implementation Mongo");
        }
    }

    @SpringBootApplication
    public static class Application {

//        @Bean("postgre")
        public AddressService addressServicePostgre() {
            return new AddressServiceImplPostgre();
        }

        @Bean("mongo")
//        @Primary // ini jd primarynya tp dua" object  (postgre jg) dibuat, emmory bengkak
        public AddressService addressServiceMongo() {
            return new AddressServiceImplMongo();
        }

        @Bean
//        @Autowired // cari bean dengan tipe AddressService trs masukkan ke addressService
//        @Qualifier("Postgre")
        public AddressController addressController(AddressService addressService) {
            AddressController controller = new AddressController();
            controller.setAddressService(addressService);
            return controller;
        }

    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
        AddressController controller = context.getBean(AddressController.class);

        controller.saveAddress("", "", "");
    }


}
