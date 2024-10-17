package com.connect.spring.kafka.listeners;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventListener {

    @KafkaListener(
            topics = "#{kafkaTopicConfig.employeeTopicName}",
            groupId = "#{kafkaTopicConfig.employeeTopicConsumerGroupId}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

}
