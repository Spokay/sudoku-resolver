package com.spokay.sudokusolver.builder;

import com.spokay.sudokusolver.model.SudokuGame;
import com.spokay.sudokusolver.model.grid.GridCreationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SudokuBuilder {
    GridBuilder gridBuilder;
    public SudokuGame buildSudokuFromGridData(GridCreationDTO gridCreationDTO){
        return SudokuGame.builder()
                .grid(gridBuilder
                        .buildFromData(gridCreationDTO.getGridStringData()))
                .turn(0)
                .build();
    }
}
