package com.sanfelice.kafkastreams;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SpringBootApplication
public class KafkaStreamsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamsDemoApplication.class, args);
    }

}
