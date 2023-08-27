package com.spokay.sudokusolver.builder;

import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import com.spokay.sudokusolver.model.sudokugame.SudokuGame;
import com.spokay.sudokusolver.model.grid.GridCreationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ClassicSudokuBuilder implements SudokuBuilder{
    ClassicGridBuilder gridBuilder;
    public ClassicSudokuGame buildSudokuFromGridData(GridCreationDTO gridCreationDTO) throws IOException {
        return ClassicSudokuGame.builder()
                .grid(gridBuilder
                        .build(gridCreationDTO))
                .turn(0)
                .build();
    }
}
