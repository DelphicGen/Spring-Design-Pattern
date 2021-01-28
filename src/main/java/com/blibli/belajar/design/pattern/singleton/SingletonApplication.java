package com.blibli.belajar.design.pattern.singleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class SingletonApplication {
    @SpringBootApplication
    public static class Configuration {
        @Bean // singleton denga tipe data Contoh, dan manggil emthod nya
        public Contoh contoh() {
            return new Contoh();
        }
    }

    public static class Contoh {
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SingletonApplication.Configuration.class);
        Contoh contoh1 = context.getBean(Contoh.class);
        Contoh contoh2 = context.getBean(Contoh.class);

//        Kalau manual, bikin controller perlu service hrs set satu"
//        A a = new A();
//        B b = new B();
//        b.setA(a);
//        b.setC(c);
//        b.setD(d);
//        Kalau pake spring akan diinject otomatis a ke b
//        A a = context.getBena(A.class);
//        B b = context.getBean(B.class); saat B.class semua dependencynya bakal di inject oleh spring

//        Kalau bikin objectnya manual (new Contoh), pasti false karena dua object yg berbeda
        System.out.println(contoh1 == contoh2);
    }
}
