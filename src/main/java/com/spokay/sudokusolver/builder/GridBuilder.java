package com.spokay.sudokusolver.builder;

import com.spokay.sudokusolver.model.grid.Grid;
import com.spokay.sudokusolver.model.grid.GridCreationDTO;

import java.io.IOException;

public interface GridBuilder {
    Grid build(GridCreationDTO gridCreationDTO) throws IOException;
}
