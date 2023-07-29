package com.spokay.sudokusolver.builder;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.grid.Grid;
import com.spokay.sudokusolver.model.grid.GridCreationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class ClassicGridBuilder implements GridBuilder{
    CaseBuilder caseBuilder;
    @Override
    public Grid build(GridCreationDTO gridCreationDTO) throws IOException {
        Case[][] cases = caseBuilder.buildAllFromDataAndSize(gridCreationDTO.getGridStringData(), gridCreationDTO.getGridHeight(), gridCreationDTO.getGridWidth());

        System.out.println(Arrays.deepToString(cases));
        return null;
    }
}
