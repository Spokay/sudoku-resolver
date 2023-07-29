package com.spokay.sudokusolver.builder;

import com.spokay.sudokusolver.model.SudokuGame;
import com.spokay.sudokusolver.model.grid.GridCreationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class SudokuBuilder {
    GridBuilder gridBuilder;
    public SudokuGame buildSudokuFromGridData(GridCreationDTO gridCreationDTO) throws IOException {
        return SudokuGame.builder()
                .grid(gridBuilder
                        .build(gridCreationDTO))
                .turn(0)
                .build();
    }
}
