package com.manjunath.dotikol.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Spring will load during start time
public class AwsConfig {

    @Bean
    public AmazonS3 s3() {
        //learn more in this link: https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
        AWSCredentials awsCredentials = new BasicAWSCredentials(

        );
       return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
