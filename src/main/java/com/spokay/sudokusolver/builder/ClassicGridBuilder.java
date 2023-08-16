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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class ClassicGridBuilder implements GridBuilder{
    CaseBuilder caseBuilder;
    ShapeBuilder shapeBuilder;
    @Override
    public Grid build(GridCreationDTO gridCreationDTO) throws IOException {
        Case[][] cases = caseBuilder.buildAllFromDataAndSize(gridCreationDTO.getGridStringData(), gridCreationDTO.getGridHeight(), gridCreationDTO.getGridWidth());

        HashMap<String, List<LineShape>> lines = shapeBuilder.buildLinesFromCases(cases);

        HashMap<Integer, List<SquareShape>> squares = shapeBuilder.buildSquaresFromCases(cases, gridCreationDTO.getSquareSize());

        System.out.println(Arrays.deepToString(cases));
//        System.out.println(lines);
//        System.out.println(squares);

        return ClassicGrid.builder()
                .cases(cases)
                .lines(lines)
                .squares(squares)
                .height(gridCreationDTO.getGridHeight())
                .width(gridCreationDTO.getGridWidth())
                .build();
    }
}
