package com.paulocdev.aws.textservice;

import io.awspring.cloud.s3.S3Template;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.io.ByteArrayInputStream;
import java.util.function.Function;

@Configuration
public class S3Functions {

    private final String BUCKET_NAME = "paulocdev-text-service-bucket-20250619";
    private final S3Template s3Template;

    public S3Functions(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    @Bean
    public Function<String, String> hello() {
        return input -> "Hello " + input + " your lambda is working!";
    }

    @Bean
    public Function<Message<String>, String> createFile() {
        return message -> {
            String content = message.getPayload();

            String fileName = "my-file" + System.currentTimeMillis() + ".txt";

            s3Template.upload(BUCKET_NAME, fileName, new ByteArrayInputStream(content.getBytes()));

            return "Successfully created file '" + fileName + "' in S3 bucket '" + BUCKET_NAME + "'.";
        };
    }
}