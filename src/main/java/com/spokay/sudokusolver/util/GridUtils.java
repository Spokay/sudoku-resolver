package com.spokay.sudokusolver.util;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.cases.CaseState;
import com.spokay.sudokusolver.model.grid.ClassicGrid;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.shape.SquareShape;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class GridUtils {
    public static boolean isTheLastEmptyCaseInLine(ClassicGrid sudokuGrid, Integer numberToCheck, Case caseChecked, String axis){
        return (!sudokuGrid.getLineByColumnNumber(caseChecked.getCoords().get(axis)).containsNumber(numberToCheck)) && (sudokuGrid.getLineByColumnNumber(caseChecked.getCoords().get(axis)).hasOneEmptyCaseRemaining()) && (caseChecked.isEmpty());
    }

     public static Optional<Integer> getFirstMissingNumber(ClassicGrid grid, LineShape lineShape){
        for (int i = 1; i <= grid.getWidth(); i++) {
            if (!lineShape.containsNumber(i)){
                return Optional.of(i);
            }
        }
         return Optional.empty();
     }

    public static ArrayList<Case> getAllCaseByType(CaseState caseState, Case[][] allCases){
        ArrayList<Case> operatedCases = new ArrayList<>();
        Arrays.stream(allCases)
                .forEach(caseArr -> Arrays.stream(caseArr)
                        .filter(caseJ -> caseJ.getCaseState().equals(caseState))
                        .forEach(operatedCases::add)
                );
        return operatedCases;
    }

    public static HashMap<Integer, Integer> getAmountsOfValuesForEachNumbers(Case[][] cases){
        HashMap<Integer, Integer> valuesAmounts = new HashMap<>();

        for (int i = 1; i < cases.length + 1; i++) {
            int finalI = i;
            AtomicInteger counter = new AtomicInteger();
            Arrays.stream(cases)
                    .forEach(caseArr -> Arrays.stream(caseArr)
                        .filter(caseJ -> !caseJ.isEmpty() && caseJ.getValue() == finalI)
                        .forEach(caseJ -> counter.getAndIncrement())
                );

            valuesAmounts.put(i, counter.get());
        }
        return valuesAmounts;
    }

    public static boolean numberIsInSquare(Integer number, SquareShape squareShape){
        AtomicBoolean result = new AtomicBoolean(false);
        Arrays.stream(squareShape.getCases())
                .forEach(caseArr -> Arrays.stream(caseArr)
                        .forEach(caseVal -> {
                            if (Objects.equals(caseVal.getValue(), number)){
                                result.set(true);
                            }
                        })
                );
        return result.get();
    }
}
