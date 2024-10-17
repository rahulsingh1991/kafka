package com.connect.spring.kafka.consumers;

import com.connect.spring.kafka.config.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractKafkaConsumerConfig {

    @Autowired
    private KafkaConfig config;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootStrapServer());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.getOffsetReset());//Possible value are earliest, latest, none
        populateConsumerSpecificProp(props);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1);
        factory.setBatchListener(true);

        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        // Set up retry handling
        // Configure the retry behavior with DefaultErrorHandler
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                new FixedBackOff(1000L, 3L) // Retry every 1 second, up to 3 times
        );
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }

    public abstract void populateConsumerSpecificProp(Map<String, Object> props);
}
