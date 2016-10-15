package com.danny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class DannyStorageWebHerokuApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(DannyStorageWebHerokuApplication.class, args);
  }
  // public static void main(String[] args) {
  // ApplicationContext ctx = SpringApplication.run(DannyStorageWebHerokuApplication.class, args);
  //
  // System.out.println("Let's inspect the beans provided by Spring Boot:");
  //
  // String[] beanNames = ctx.getBeanDefinitionNames();
  // Arrays.sort(beanNames);
  // for (String beanName : beanNames) {
  // System.out.println(beanName);
  // }
  //
  // }

}
