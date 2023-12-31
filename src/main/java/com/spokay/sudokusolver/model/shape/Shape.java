package com.spokay.sudokusolver.model.shape;

import com.spokay.sudokusolver.model.cases.Case;

import java.util.Optional;

public interface Shape {
    boolean hasOneEmptyCaseRemaining();
    boolean hasSinglesPossibilitiesRemaining();
    Optional<Case> getFirstEmptyCase();
    boolean containsNumber(Integer numberToCheck);
}
