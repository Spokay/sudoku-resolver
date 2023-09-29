package com.spokay.sudokusolver.manager.checks;

import com.spokay.sudokusolver.model.grid.ClassicGrid;
import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import org.springframework.stereotype.Service;

@Service
public class PossibilityEliminator {

    public void eliminatePossibilities(ClassicGrid classicGrid){
        // get the amount of occurences for each numbers on the grid
//        HashMap<Integer, Integer> valuesAmounts = GridUtils.getAmountsOfValuesForEachNumbers(sudokuGrid.getCases());

        //convert the Map to a list of Entry to sort it by value
//        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(valuesAmounts.entrySet());

        // sort the Entries by value by reverse order
//        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // check the Singles in lines for each numbers in descending order (max -> min)
//        list.forEach(pair -> eliminateAllPossibilities(sudokuGrid, pair.getKey(), "rows"));
//        list.forEach(pair -> eliminateAllPossibilities(sudokuGrid, pair.getKey(), "columns"));
    }
    public void eliminatePossibilitiesInLine(){

    }
}
