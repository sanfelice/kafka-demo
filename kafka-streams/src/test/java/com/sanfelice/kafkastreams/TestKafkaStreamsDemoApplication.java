package com.sanfelice.kafkastreams;

import com.sanfelice.kafkastreams.containers.CustomKafkaContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;

@TestConfiguration(proxyBeanMethods = false)
public class TestKafkaStreamsDemoApplication {

    @Bean
    @ServiceConnection
    CustomKafkaContainer kafkaContainer() {
        return new CustomKafkaContainer();
    }

    public static void main(String[] args) {
        SpringApplication.from(KafkaStreamsDemoApplication::main).with(TestKafkaStreamsDemoApplication.class).run(args);
    }

}
