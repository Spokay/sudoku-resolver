package com.spokay.sudokusolver.manager;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import com.spokay.sudokusolver.util.GridUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassicSudokuGameManager{
    public void nextTurn(ClassicSudokuGame sudokuGame) {
        System.out.println("game manager next turn called");

        checkAllSingles(sudokuGame.getGrid().getCases());
    }

    public void checkAllSingles(Case[][] gridCases){
        // get the amount of occurences for each numbers on the grid
        HashMap<Integer, Integer> valuesAmounts = GridUtils.getAmountsOfValuesForEachNumbers(gridCases);

        //convert the Map to a list of Entry to sort it by value
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(valuesAmounts.entrySet());

        // sort the Entries by value by reverse order
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // check the Singles for each numbers in descending order (max -> min)
        list.forEach(pair -> checkSinglesForSpecificNumber(pair.getKey()));
    }

    public List<Case> checkSinglesForSpecificNumber(Integer numberToCheck){
        System.out.println(numberToCheck);
        //todo: check the Singles for this specific number
        return null;
    }
}
