package com.connect.spring.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {

    @Value("${kafka.bootstrap-servers:localhost:9092}")
    private String bootStrapServer;

    @Value("${kafka.auto.offset.reset}")
    private String offsetReset;


    public String getBootStrapServer() {
        return bootStrapServer;
    }

    public String getOffsetReset() {
        return offsetReset;
    }


}
