package com.spokay.sudokusolver.manager;

import com.spokay.sudokusolver.manager.checks.PossibilityEliminator;
import com.spokay.sudokusolver.manager.checks.SingleEmptyChecker;
import com.spokay.sudokusolver.manager.checks.SinglePossibilityChecker;
import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import com.spokay.sudokusolver.util.GridUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ClassicSudokuGameManager{

    SinglePossibilityChecker singlePossibilityChecker;

    SingleEmptyChecker singleEmptyChecker;

    PossibilityEliminator possibilityEliminator;
    public int nextTurn(ClassicSudokuGame sudokuGame) {
        System.out.println("game manager next turn called " + sudokuGame.getTurn());
        /* execute the instructions in this order :
        todo: try to use recursivity on methods to check all the possibilities step by step
        - check if there is a single values
        - check deeper with elimination
        - TBD
        */


        // First check is there is any single empty cases in lines or squares
        int singleCheckResult = singleEmptyChecker.checkAllShapeType(sudokuGame.getGrid());
        if (singleCheckResult > 0){
            return singleCheckResult;
        }

        int singlePossibilityCheckResult = singlePossibilityChecker.checkAllShapeType(sudokuGame.getGrid());
        if (singlePossibilityCheckResult > 0){
            return singlePossibilityCheckResult;
        }
        // If no single is found eliminate possibilities
        possibilityEliminator.eliminateAllPossibilities(sudokuGame.getGrid());

        // increment the number of turn spend on the sudoku
        sudokuGame.setTurn(sudokuGame.getTurn() + 1);

         return singleCheckResult + singlePossibilityCheckResult;
    }


    public boolean resolveAllCases(ClassicSudokuGame sudokuGame) {
        while(!sudokuGame.isFinished() && sudokuGame.getTurn() < 100){
            int turnResult = nextTurn(sudokuGame);
            System.out.println(turnResult + " case have been found this turn");
        }
        if(sudokuGame.isFinished()){
            return sudokuGame.isFinished();
        }else{
            backtrackLoop(sudokuGame);
        }
        return false;
    }

    private void backtrackLoop(ClassicSudokuGame sudokuGame) {
        System.out.println("backtrack loop started");
        Case[][] copyOfGridCase = GridUtils.buildCopyOfCases(sudokuGame.getGrid().getCases());
        for (int row = 0; row < sudokuGame.getGrid().getWidth(); row++) {
            for (int col = 0; col < sudokuGame.getGrid().getWidth(); col++) {
                for (int numberToCheck = 0; numberToCheck < sudokuGame.getGrid().getWidth(); numberToCheck++) {
                    if(isValid(numberToCheck, copyOfGridCase[row][col])){
                        copyOfGridCase[row][col].setValue(numberToCheck);
                    }else{
                        if(col == 0){
                            col = sudokuGame.getGrid().getWidth() - 1;
                            row--;
                        }else{
                            col--;
                        }
                    }
                }
            }
        }
    }

    boolean isValid(int number, Case caseToCheck){

        return true;
    }

}
