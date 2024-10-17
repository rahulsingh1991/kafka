package com.connect.spring.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("kafkaTopicConfig")
public class TopicConfig {

    @Value("#{ '${app.environment.type}' + '.' + '${kafka.employee.topic.name}' }")
    public String employeeTopicName;

    @Value("#{ '${app.environment.type}' + '.' + '${kafka.employee.topic.name}' + '.' + '${kafka.consumer.group.id}' }")
    public String employeeTopicConsumerGroupId;


}
