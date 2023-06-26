package com.revature.marstown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MarsTownApplication {
  public static void main(String args[]) {
    SpringApplication.run(MarsTownApplication.class, args);
  }
}
