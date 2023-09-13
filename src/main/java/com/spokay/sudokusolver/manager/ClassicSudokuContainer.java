package com.spokay.sudokusolver.manager;

import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClassicSudokuContainer {
    public Integer sudokuId;
    public ClassicSudokuGame sudokuHolded;
}
