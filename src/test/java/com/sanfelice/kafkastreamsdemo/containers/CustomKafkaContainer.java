package com.sanfelice.kafkastreamsdemo.containers;

import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public class CustomKafkaContainer extends KafkaContainer {

    private static final DockerImageName IMAGE = DockerImageName.parse("confluentinc/cp-kafka").withTag("latest");

    public CustomKafkaContainer() {
        super(IMAGE);
    }

    public void initialize() {

    }
}
