package com.spokay.sudokusolver.model.sudokugame;

import com.spokay.sudokusolver.model.cases.CaseState;
import com.spokay.sudokusolver.model.grid.ClassicGrid;
import com.spokay.sudokusolver.util.GridUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClassicSudokuGame implements SudokuGame{
    private ClassicGrid grid;
    private Integer turn;
    public boolean isFinished(){
        return GridUtils.getAllCaseByType(CaseState.EMPTY_CASE, grid.getCases()).isEmpty();
    }
}
