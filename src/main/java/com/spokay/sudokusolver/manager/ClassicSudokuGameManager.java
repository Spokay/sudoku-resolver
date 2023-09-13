package com.spokay.sudokusolver.manager;

import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import org.springframework.stereotype.Service;

@Service
public class ClassicSudokuGameManager{
    public void nextTurn(ClassicSudokuGame sudokuGame) {
        System.out.println("game manager next turn called");
    }
}
