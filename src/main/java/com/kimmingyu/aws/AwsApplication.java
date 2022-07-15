package com.kimmingyu.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AwsApplication {

    // test
    public static void main(String[] args) {

        SpringApplication.run(AwsApplication.class, args);

    }

}
