package com.connect.spring.kafka.config;

import com.connect.spring.kafka.consumers.EmployeeConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeConsumerConfigTest {

    @Autowired
    private EmployeeConsumerConfig employeeConsumerConfig;

    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    @Test
    void consumerFactory_shouldContainGroupIdConfig() {
        Map<String, Object> consumerProps = consumerFactory.getConfigurationProperties();

        assertThat(consumerProps).containsKey(ConsumerConfig.GROUP_ID_CONFIG);
        String groupId = (String) consumerProps.get(ConsumerConfig.GROUP_ID_CONFIG);
        assertThat(groupId).isEqualTo("test.myConsumer.employee");  // Replace with expected groupId
    }

    @Test
    void consumerFactory_shouldContainBootstrapServersConfig() {
        Map<String, Object> consumerProps = consumerFactory.getConfigurationProperties();

        assertThat(consumerProps).containsKey(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG);
        String bootstrapServers = (String) consumerProps.get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG);
        assertThat(bootstrapServers).isNotEmpty();
    }

    @Test
    void consumerFactory_shouldUseStringDeserializers() {
        Map<String, Object> consumerProps = consumerFactory.getConfigurationProperties();

        assertThat(consumerProps.get(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG))
                .isEqualTo(StringDeserializer.class);
        assertThat(consumerProps.get(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG))
                .isEqualTo(StringDeserializer.class);
    }

    @Test
    void consumerFactoryBean_shouldExist() {
        assertThat(consumerFactory).isNotNull();
    }

    @Test
    void consumerFactory_shouldBeConfiguredProperly() {
        Map<String, Object> consumerProps = consumerFactory.getConfigurationProperties();

        assertThat(consumerProps).isNotEmpty();
        assertThat(consumerProps).containsKeys(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                ConsumerConfig.GROUP_ID_CONFIG
        );
    }
}