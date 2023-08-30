package org.recap;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * The main class is used to launch the spring boot application.
 */
@PropertySource("classpath:application.properties")
@SpringBootApplication
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private ApplicationContext context;
    @Bean
    public CamelContext camelContext() {
        return new SpringCamelContext(context);
    }
}
