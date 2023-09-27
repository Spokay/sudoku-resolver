package com.spokay.sudokusolver.manager;

import com.spokay.sudokusolver.model.cases.CaseState;
import com.spokay.sudokusolver.model.grid.ClassicGrid;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import com.spokay.sudokusolver.util.GridUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassicSudokuGameManager{
    public void nextTurn(ClassicSudokuGame sudokuGame) {
        System.out.println("game manager next turn called");
        /* execute the instructions in this order :
        - check if there is a single values
        - check deeper with elimination
        - TBD
        */
        checkAllSingles(sudokuGame.getGrid());
    }

    public void checkAllSingles(ClassicGrid sudokuGrid){
        // get the amount of occurences for each numbers on the grid
        HashMap<Integer, Integer> valuesAmounts = GridUtils.getAmountsOfValuesForEachNumbers(sudokuGrid.getCases());

        //convert the Map to a list of Entry to sort it by value
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(valuesAmounts.entrySet());

        // sort the Entries by value by reverse order
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // check the Singles for each numbers in descending order (max -> min)
        list.forEach(pair -> checkSinglesForSpecificNumberInLine(sudokuGrid, pair.getKey(), "rows"));
        list.forEach(pair -> checkSinglesForSpecificNumberInLine(sudokuGrid, pair.getKey(), "columns"));
    }

    public void checkSinglesForSpecificNumberInLine(ClassicGrid sudokuGrid, Integer numberToCheck, String direction){
        String axis = Objects.equals(direction, "rows") ? "x" : "y";
        sudokuGrid.getLines().get(direction).stream()
                .filter(LineShape::hasOneEmptyCaseRemaining)
                .forEach(lineShape -> lineShape.getFirstEmptyCase()
                        .ifPresent(caseFound -> {
                            caseFound.setValue(GridUtils.getFirstMissingNumber(sudokuGrid, lineShape).orElseThrow());
                            caseFound.setCaseState(CaseState.FILLED_CASE);
                        })
                );
        /*sudokuGrid.getLines().get("rows").forEach(lineShape -> {
                    Arrays.stream(lineShape.getLineCases())
                            .filter(caseInRow -> !sudokuGrid.getLineByColumnNumber(caseInRow.getCoords().get("x")).containsNumber(numberToCheck) && !caseInRow.getClass().equals(FilledCase.class)
                            ).forEach(caseInRow -> caseInRow.getPossibleValue().add(numberToCheck));
                }
        );*/

    }
}
