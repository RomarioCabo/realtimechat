package com.br.realtimechat.infrastructure.configuration;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.LOWER_CAMEL_CASE;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Configuration
public class JacksonConfiguration {

    @Bean("defaultObjectMapper")
    @Primary
    public ObjectMapper defaultObjectMapper() {
        return buildObjectMapper();
    }

    private ObjectMapper buildObjectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .propertyNamingStrategy(LOWER_CAMEL_CASE)
                .featuresToDisable(WRITE_DATES_AS_TIMESTAMPS)
                .featuresToDisable(ACCEPT_FLOAT_AS_INT)
                .featuresToDisable(FAIL_ON_UNKNOWN_PROPERTIES)
                .featuresToDisable(FAIL_ON_MISSING_CREATOR_PROPERTIES)
                .featuresToEnable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .modules(new JavaTimeModule())
                .build();
    }
}
