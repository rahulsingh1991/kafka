package com.connect.spring.kafka.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeEventListener {

    @KafkaListener(
            topics = "#{kafkaTopicConfig.employeeTopicName}",
            groupId = "#{kafkaTopicConfig.employeeTopicConsumerGroupId}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(List<String> messages, Acknowledgment acknowledgment) {
        try {
            // Process each message
            messages.forEach(message -> {
                System.out.println("Processing message: " + message);
            });

            // Manually commit offsets after successfully processing the batch
            acknowledgment.acknowledge();
            System.out.println("Offsets have been acknowledged.");
        } catch (Exception e) {
            // Handle exception, decide what to do with unprocessed messages may
            System.err.println("Error processing batch: " + e.getMessage());
        }

    }

}
