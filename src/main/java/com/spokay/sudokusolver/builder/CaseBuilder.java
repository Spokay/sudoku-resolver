package com.spokay.sudokusolver.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.cases.EmptyCase;
import com.spokay.sudokusolver.model.cases.FilledCase;
import com.spokay.sudokusolver.parser.GridStringDataParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
@AllArgsConstructor
public class CaseBuilder {
    GridStringDataParser stringDataParser;
    public Case[][] buildAllFromDataAndSize(String gridStringData, Integer gridHeight, Integer gridWidth) throws JsonProcessingException {
        // parse the string data to JsonNode
        JsonNode jsonDataValue = stringDataParser.parseStringDataToJsonNode(gridStringData);

        // initialize a Case array with give grid height and width
        Case[][] cases = new Case[gridHeight][gridWidth];

        // iterate through y-axis on the jsonNode fields
        jsonDataValue.fields().forEachRemaining(y -> {
            // get the value of the current y iteration as an ArrayNode
            ArrayNode arrayNode = (ArrayNode) y.getValue();

            // get an iterator of the ArrayNode elements
            Iterator<JsonNode> elements = arrayNode.elements();
            // initialize a counter for the ArrayNode iteration
            int xIndex = 0;
            // iterate over x-axis
            while(elements.hasNext()){
                JsonNode xValue = elements.next();
                // fill the cases array with the current iteration value
                cases[Integer.parseInt(y.getKey())][xIndex] = xValue.intValue() == 0 ? new EmptyCase() : new FilledCase(xValue.intValue());
                xIndex++;
            }
        });

        return cases;
    }
}
