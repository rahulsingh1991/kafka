package com.connect.spring.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentConfig {

    @Value("${app.environment.type:development}")
    private String environmentType;

    public String getEnvironmentType() {
        return environmentType;
    }

}
