package com.connect.spring.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Map;

@EnableKafka
@Configuration
public class EmployeeConsumerConfig extends AbstractKafkaConsumerConfig {
    @Autowired
    private TopicConfig topicConfig;

    @Override
    public void populateConsumerSpecificProp(Map<String, Object> props) {
        props.put(ConsumerConfig.GROUP_ID_CONFIG, topicConfig.employeeTopicConsumerGroupId);
    }
}
