package com.spokay.sudokusolver.builder;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.grid.ClassicGrid;
import com.spokay.sudokusolver.model.grid.Grid;
import com.spokay.sudokusolver.model.grid.GridCreationDTO;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.shape.SquareShape;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class ClassicGridBuilder implements GridBuilder{
    CaseBuilder caseBuilder;
    ClassicShapeBuilder classicShapeBuilder;
    @Override
    public ClassicGrid build(GridCreationDTO gridCreationDTO) throws IOException {
        // build cases
        Case[][] cases = caseBuilder.buildAllFromDataAndSize(gridCreationDTO.getGridStringData(), gridCreationDTO.getGridHeight(), gridCreationDTO.getGridWidth());

        // build the lines shapes of the grid
        HashMap<String, List<LineShape>> lines = classicShapeBuilder.buildLinesFromCases(cases);

        // build the squares shapes of the grid
        HashMap<Integer, List<SquareShape>> squares = classicShapeBuilder.buildSquaresFromCases(cases, gridCreationDTO.getSquareSize());

        return ClassicGrid.builder()
                .cases(cases)
                .lines(lines)
                .squares(squares)
                .squareSize(gridCreationDTO.getSquareSize())
                .height(gridCreationDTO.getGridHeight())
                .width(gridCreationDTO.getGridWidth())
                .build();
    }
}
