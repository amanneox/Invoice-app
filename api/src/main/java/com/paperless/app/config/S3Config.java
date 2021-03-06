package com.paperless.app.config;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3 s3client() {

        AWSCredentialsProvider awsCreds = new ClasspathPropertiesFileCredentialsProvider();

        return (AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(awsCreds)
                .build());
    }
}