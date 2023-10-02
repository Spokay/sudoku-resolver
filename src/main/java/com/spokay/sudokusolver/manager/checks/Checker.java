package com.spokay.sudokusolver.manager.checks;

import com.spokay.sudokusolver.model.grid.ClassicGrid;

public interface Checker {
    int checkLineShape(ClassicGrid sudokuGrid, String direction);

    int checkAllShapeType(ClassicGrid sudokuGrid);

    int checkSquareShape(ClassicGrid sudokuGrid);
}
