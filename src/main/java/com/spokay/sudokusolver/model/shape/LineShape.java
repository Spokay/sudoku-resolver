package com.spokay.sudokusolver.model.shape;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.cases.CaseState;
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

    public boolean hasOneEmptyCaseRemaining() {
        return Arrays.stream(lineCases)
                .filter(caseInArray -> caseInArray.getCaseState().equals(CaseState.EMPTY_CASE))
                .toList()
                .size() == 1;
    }
}
