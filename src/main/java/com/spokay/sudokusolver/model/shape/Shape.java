package com.spokay.sudokusolver.model.shape;

import com.spokay.sudokusolver.model.cases.Case;

import java.util.Optional;

public interface Shape {
    boolean hasOnePossibilityRemaining();
    Optional<Case> getFirstEmptyCase();
    boolean containsNumber(Integer numberToCheck);
}
