package com.bilvantis.user.app;

import com.bilvantis.user.api.UserServiceAutoConfiguration;
import com.bilvantis.user.dao.UserDaoAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages={
        "com.bilvantis.user.app.*",
})
@Import({UserDaoAutoConfiguration.class, UserServiceAutoConfiguration.class})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}

