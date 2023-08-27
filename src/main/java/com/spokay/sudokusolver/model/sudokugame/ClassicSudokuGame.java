package com.spokay.sudokusolver.model.sudokugame;

import com.spokay.sudokusolver.model.grid.ClassicGrid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClassicSudokuGame implements SudokuGame{
    private ClassicGrid grid;
    private Integer turn;
    public boolean isFinished(){
        return grid.getAllCaseByType("EmptyCase", grid.getCases()).isEmpty();
    }
}
