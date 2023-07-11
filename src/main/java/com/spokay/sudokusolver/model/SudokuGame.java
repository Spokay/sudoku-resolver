package com.spokay.sudokusolver.model;

import com.spokay.sudokusolver.model.grid.Grid;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class SudokuGame {
    Grid grid;
    Integer turn;
    public boolean isFinished(){
        return false;
    }
}
