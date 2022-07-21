package com.kimmingyu.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.kimmingyu.aws.model.Role;
import com.kimmingyu.aws.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AwsApplication {

    // test
    public static void main(String[] args) {
        SpringApplication.run(AwsApplication.class, args);
    }
    @Bean
    CommandLineRunner init(RoleRepository roleRepository) {

        return args -> {

            Role adminRole = roleRepository.findByRole("ADMIN");
            if (adminRole == null) {
                Role newAdminRole = new Role();
                newAdminRole.setRole("ADMIN");
                roleRepository.save(newAdminRole);
            }

            Role userRole = roleRepository.findByRole("MEMBER");
            if (userRole == null) {
                Role newUserRole = new Role();
                newUserRole.setRole("MEMBER");
                roleRepository.save(newUserRole);
            }
        };

    }


}
