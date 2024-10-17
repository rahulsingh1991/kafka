package com.connect.spring.kafka.producer;

import com.connect.spring.kafka.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final TopicConfig topicConfig;

    @Autowired
    public EmployeeEventProducer(KafkaTemplate<String, String> kafkaTemplate, TopicConfig topicConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicConfig = topicConfig;
    }

    public void sendEmployeeEvent(String employeeEvent) {
        String topicName = "development.employee"; // The topic you want to send messages to

        // Send the message and handle the future
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicConfig.employeeTopicName, employeeEvent);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Failed to send message: " + ex.getMessage());
            } else {
                System.out.println("Message sent successfully: " + result.getRecordMetadata());
            }
        });
    }
}