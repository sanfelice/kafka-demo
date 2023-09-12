package com.sanfelice.kafkastreams.kafka;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.processor.api.ContextualFixedKeyProcessor;
import org.apache.kafka.streams.processor.api.FixedKeyRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopologyBuilder {

    @Bean
    public StreamsBuilder build(StreamsBuilder builder) {
        var stringSerde = new Serdes.StringSerde();
        var consumed = Consumed.with(stringSerde, stringSerde);
        builder.stream("com.sanfelice.test.v1", consumed)
                .processValues(Processor::new)
                .foreach((key, value) -> {
                    log.atInfo()
                            .addKeyValue("value", value)
                            .log("Received message");
                });
        return builder;
    }


    static class Processor extends ContextualFixedKeyProcessor<String, String, String> {

        @SneakyThrows
        @Override
        public void process(FixedKeyRecord<String, String> record) {
            context().forward(record);
            var count = 0;
            do {
                log.atInfo()
                        .addKeyValue("key", record.key())
                        .addKeyValue("value", record.value())
                        .addKeyValue("count", count)
                        .log("Processing message");
                count++;
                Thread.sleep(1000);
            } while (count < 100);
        }
    }

}
