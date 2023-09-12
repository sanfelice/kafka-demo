package com.sanfelice.kafkastreams.containers;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CustomKafkaContainer extends KafkaContainer {

    private static final DockerImageName IMAGE = DockerImageName.parse("confluentinc/cp-kafka").withTag("latest");

    public CustomKafkaContainer() {
        super(IMAGE);
    }

    public void initialize() {
        Map<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, this.getBootstrapServers());
        props.put(AdminClientConfig.CLIENT_ID_CONFIG, "admin-connfig");
        AdminClientConfig config = new AdminClientConfig(props);
        try (Admin admin = Admin.create(props)) {
            int partitions = 1;
            short replicationFactor = 1;
            var topicName = "com.sanfelice.test.v1";
            NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);

            CreateTopicsResult result = admin.createTopics(
                    Collections.singleton(newTopic)
            );

            KafkaFuture<Void> future = result.values().get(topicName);
            future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
