package com.spokay.sudokusolver.manager;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.cases.FilledCase;
import com.spokay.sudokusolver.model.grid.ClassicGrid;
import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import com.spokay.sudokusolver.util.GridUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassicSudokuGameManager{
    public void nextTurn(ClassicSudokuGame sudokuGame) {
        System.out.println("game manager next turn called");

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
        list.forEach(pair -> checkSinglesForSpecificNumber(sudokuGrid, pair.getKey()));
    }

    public List<Case> checkSinglesForSpecificNumber(ClassicGrid sudokuGrid, Integer numberToCheck){
        System.out.println(numberToCheck);
        List<Case> unclearedCasesForSpecificNumber = new ArrayList<>();

        sudokuGrid.getLines().get("rows").forEach(lineShape -> {
                    Arrays.stream(lineShape.getLineCases())
                            .filter(caseInRow -> !sudokuGrid.getLineByColumnNumber(caseInRow.getCoords().get("x")).containsNumber(numberToCheck) || !caseInRow.getClass().equals(FilledCase.class)
                            ).forEach(unclearedCasesForSpecificNumber::add);
                }
        );

        System.out.println(unclearedCasesForSpecificNumber);
        // possible values = not in the rows and columns next to the square
        // todo: its not working :(
        // if no number in the line and column does not contains it

        return unclearedCasesForSpecificNumber;
    }
}
