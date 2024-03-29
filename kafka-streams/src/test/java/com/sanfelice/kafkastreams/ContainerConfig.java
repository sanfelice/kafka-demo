package com.sanfelice.kafkastreams;

import com.sanfelice.kafkastreams.containers.CustomKafkaContainer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ContainerConfig {

    private final CustomKafkaContainer kafkaContainer;

    @PostConstruct
    public void postConstruct() {
        kafkaContainer.initialize();
    }
}
