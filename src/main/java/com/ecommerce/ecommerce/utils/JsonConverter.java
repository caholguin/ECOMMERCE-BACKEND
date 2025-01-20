package com.ecommerce.ecommerce.utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonConverter implements AttributeConverter<List<Map<String, String>>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Map<String, String>> attribute){
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting JSON to String", e);
        }
    }

    @Override
    public List<Map<String, String>> convertToEntityAttribute(String dbData){
        try {
            return objectMapper.readValue(dbData, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting String to JSON", e);
        }
    }
}