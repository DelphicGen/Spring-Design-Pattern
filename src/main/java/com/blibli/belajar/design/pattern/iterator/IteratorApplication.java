package com.blibli.belajar.design.pattern.iterator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class IteratorApplication {
    public enum PaymentType {
        CREDIT_CARD, BCA, MANDIRI, BRI
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Payment {
        private PaymentType paymentType;
        private Long amount;
    }

    public static interface PaymentService {
        boolean isSupport(PaymentType paymentType);
        void pay(Payment payment);
    }

    public static class CreditCardPaymentService implements PaymentService {

        @Override
        public boolean isSupport(PaymentType paymentType) {
            return paymentType.equals(PaymentType.CREDIT_CARD);
        }

        @Override
        public void pay(Payment payment) {
            System.out.println("Bayar pakai credit card");
        }
    }

    public static class MandiriPaymentService implements PaymentService {

        @Override
        public boolean isSupport(PaymentType paymentType) {
            return paymentType.equals(PaymentType.MANDIRI);
        }

        @Override
        public void pay(Payment payment) {
            System.out.println("Bayar pakai mandiri");
        }
    }

    public static class BCAPaymentService implements PaymentService {

        @Override
        public boolean isSupport(PaymentType paymentType) {
            return paymentType.equals(PaymentType.BCA);
        }

        @Override
        public void pay(Payment payment) {
            System.out.println("Bayar pakai bca");
        }
    }

    public static class BRIPaymentService implements PaymentService {

        @Override
        public boolean isSupport(PaymentType paymentType) {
            return paymentType.equals(PaymentType.BRI);
        }

        @Override
        public void pay(Payment payment) {
            System.out.println("Bayar pakai BRI");
        }
    }

    @SpringBootApplication
    public static class Application {
        @Bean
        public CreditCardPaymentService creditCardPaymentService() {
            return new CreditCardPaymentService();
        }
        @Bean
        public MandiriPaymentService mandiriPaymentService() {
            return new MandiriPaymentService();
        }

        @Bean
        public BCAPaymentService bcaPaymentService() {
            return new BCAPaymentService();
        }
    }

    public static void main(String[] args) {
//        manual if else ga direkomendasikan
        ApplicationContext context = SpringApplication.run(Application.class);
        Payment payment = Payment.builder().paymentType(PaymentType.CREDIT_CARD).amount(10000000L).build();

        context.getBeansOfType(PaymentService.class).values()
                .stream()
                .filter(paymentService -> paymentService.isSupport(payment.getPaymentType()))
                .findFirst()
                .ifPresent(paymentService -> paymentService.pay(payment));

    }
}
