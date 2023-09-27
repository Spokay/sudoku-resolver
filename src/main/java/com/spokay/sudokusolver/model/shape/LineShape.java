package com.spokay.sudokusolver.model.shape;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.grid.ClassicGrid;
import lombok.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

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
            .filter(Case::isEmpty).toList().size() == 1;
    }

    public Optional<Case> getFirstEmptyCase() {
        return Arrays.stream(lineCases).filter(Case::isEmpty).findFirst();
    }
}
