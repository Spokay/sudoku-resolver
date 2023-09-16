package com.spokay.sudokusolver.model.shape;

import com.spokay.sudokusolver.model.cases.Case;
import lombok.*;

import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineShape implements Shape{
    Case[] lineCases;

    public boolean containsNumber(Integer numberToCheck){
        return Arrays.stream(lineCases)
                .anyMatch(caseInArray -> Objects.equals(caseInArray.getValue(), numberToCheck));
    }
}
