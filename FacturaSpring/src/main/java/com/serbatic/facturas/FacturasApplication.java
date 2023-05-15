package com.serbatic.facturas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class FacturasApplication {

  public static void main(String[] args) {
    SpringApplication.run(FacturasApplication.class, args);
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/templates/");
    resolver.setSuffix(".html");
    return resolver;
  }

}
