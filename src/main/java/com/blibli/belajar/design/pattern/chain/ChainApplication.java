package com.blibli.belajar.design.pattern.chain;

import com.blibli.belajar.design.pattern.facade.FacadeApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChainApplication {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Contoh {
        private String name;
    }

    @SpringBootApplication
    public static class Application {
        @RestController
        public static class HelloController {
            @GetMapping("/")
            public Contoh helloWorld() {
                return Contoh.builder().name("Eko").build();
//                return "Hello World";
            }
        }

        public static class PowerdByInterceptor implements HandlerInterceptor {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//                response.setHeader("X-Powered-By", "Blibli Future Program");
            }

            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.println("Pre Handle");
                return true;
//                String xApi = request.getHeader("X-API");
//                if(StringUtils.hasText(xApi)) {
//                    return true;
//                } else {
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    return false;
//                }
            }
        }

        @Component
        public static class WebConfiguration implements WebMvcConfigurer {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new PowerdByInterceptor());
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
