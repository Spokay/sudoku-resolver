package com.spokay.sudokusolver.manager.checks;

import com.spokay.sudokusolver.model.cases.Case;
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

        // check the Singles in lines for each numbers in descending order (max -> min)
        list.forEach(pair -> eliminatePossibilitiesInLine(sudokuGrid, pair.getKey(), "rows"));
        list.forEach(pair -> eliminatePossibilitiesInLine(sudokuGrid, pair.getKey(), "columns"));
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
                            }
                        })
        );
    }

}
