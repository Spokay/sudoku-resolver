package com.spokay.sudokusolver.builder;

import com.spokay.sudokusolver.model.grid.GridCreationDTO;
import com.spokay.sudokusolver.model.sudokugame.SudokuGame;

import java.io.IOException;

public interface SudokuBuilder {
    SudokuGame buildSudokuFromGridData(GridCreationDTO gridCreationDTO) throws IOException;
}
