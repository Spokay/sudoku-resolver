package com.spokay.sudokusolver.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GridStringDataParser {
    ObjectMapper objectMapper;
    public JsonNode parseStringDataToJsonNode(String stringData) throws JsonProcessingException {
        String fixedDataJsonFormat = stringData.replace("'", "\"");
        System.out.println(fixedDataJsonFormat);

        return objectMapper.readTree(fixedDataJsonFormat);
    }
}
