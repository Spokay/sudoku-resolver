package com.spokay.sudokusolver.manager.checks;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.cases.CaseState;
import com.spokay.sudokusolver.model.grid.ClassicGrid;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.util.GridUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PossibilityEliminator {

    public void eliminateAllPossibilities(ClassicGrid sudokuGrid){
        // get the amount of occurences for each numbers on the grid
        HashMap<Integer, Integer> valuesAmounts = GridUtils.getAmountsOfValuesForEachNumbers(sudokuGrid.getCases());

        //convert the Map to a list of Entry to sort it by value
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(valuesAmounts.entrySet());

        // sort the Entries by value by reverse order
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // elminate the possibilities in each line for the most occuring numbers
        list.forEach(pair -> eliminatePossibilitiesInLine(sudokuGrid, pair.getKey(), "rows"));
        list.forEach(pair -> eliminatePossibilitiesInLine(sudokuGrid, pair.getKey(), "columns"));

        // eliminate the possibilities in each square for the most occuring numbers
        list.forEach(pair -> checkLastPossibilityInSquare(sudokuGrid, pair.getKey()));
    }

    private void eliminatePossibilitiesInLine(ClassicGrid sudokuGrid, Integer numberToCheck, String direction) {
        List<LineShape> linesInDirection = sudokuGrid.getLines().get(direction);
        linesInDirection.forEach(
                lineShape -> Arrays.stream(lineShape.getLineCases())
                    .filter(Case::isEmpty)
                    .forEach(caseInLine -> {
                        LineShape oppositeAxisLine = direction.equals("rows") ? sudokuGrid.getLineByColumnNumber(caseInLine.getCoords().get("x")) : sudokuGrid.getLineByRowNumber(caseInLine.getCoords().get("y"));
                        if (oppositeAxisLine.containsNumber(numberToCheck)){
                            caseInLine.getPossibleValue().remove(numberToCheck);
                        }else if(lineShape.containsNumber(numberToCheck)){
                            caseInLine.getPossibleValue().remove(numberToCheck);
                        }
                    })
        );
    }

    private void checkLastPossibilityInSquare(ClassicGrid sudokuGrid, Integer numberToCheck) {

        for (int row = 0; row < sudokuGrid.getSquares().size(); row++) {

            sudokuGrid.getSquares().get(row)
                    .stream()
                    .filter(
                            squareInRow -> !squareInRow.containsNumber(numberToCheck)
                    )
                    .forEach(
                            square -> {
                                List<Case> possibleCases = square.getPossibleCasesForNumber(numberToCheck);

                                if (possibleCases.size() == 1){
                                    Case caseToFill = possibleCases.get(0);
                                    caseToFill.setValue(numberToCheck);
                                    caseToFill.setCaseState(CaseState.FILLED_CASE);
                                    caseToFill.getPossibleValue().clear();
                                }
                            }
                    );
        }

    }

}
