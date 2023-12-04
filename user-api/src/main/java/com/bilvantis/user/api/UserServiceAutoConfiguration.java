package com.bilvantis.user.api;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ComponentScan(basePackages={
        "com.bilvantis.user.api.*",
})
public class UserServiceAutoConfiguration {
}
