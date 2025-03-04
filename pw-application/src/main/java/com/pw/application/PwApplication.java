package com.pw.application;

import com.pw.core.bean.PwBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.pw"}, nameGenerator = PwBeanNameGenerator.class)
public class PwApplication {

    public static void main(String[] args) {
        SpringApplication.run(PwApplication.class, args);
    }

}
