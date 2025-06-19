package com.paulocdev.aws.textservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class S3Functions {

    @Bean
    public Function<String, String> hello() {
        return input -> "Hello " + input;
    }
}