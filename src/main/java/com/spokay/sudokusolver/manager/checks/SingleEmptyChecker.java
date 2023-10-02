package com.spokay.sudokusolver.manager.checks;

import com.spokay.sudokusolver.model.cases.CaseState;
import com.spokay.sudokusolver.model.grid.ClassicGrid;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.shape.SquareShape;
import com.spokay.sudokusolver.util.GridUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SingleEmptyChecker implements Checker{
    @Override
    public int checkAllShapeType(ClassicGrid sudokuGrid){
        // check the Singles in each line and fill them if found
        int singleInRowResult = checkLineShape(sudokuGrid, "rows");
        int singleInColumnResult = checkLineShape(sudokuGrid, "columns");
        // check the Singles in each squares
        int singleInSquareResult = checkSquareShape(sudokuGrid);
        return singleInRowResult + singleInColumnResult + singleInSquareResult;
    }

    public int checkSquareShape(ClassicGrid sudokuGrid) {
        AtomicInteger singlesFound = new AtomicInteger();
        for (int row = 0; row < sudokuGrid.getSquares().size(); row++) {
            sudokuGrid.getSquares().get(row)
                    .stream()
                    .filter(SquareShape::hasOneEmptyCaseRemaining)
                    .forEach(squareInRow -> squareInRow.getFirstEmptyCase()
                        .ifPresent(caseFound -> {
                            singlesFound.getAndIncrement();
                            caseFound.setValue(GridUtils.getFirstMissingNumberInShape(sudokuGrid.getWidth(), squareInRow).orElseThrow());
                            caseFound.setCaseState(CaseState.FILLED_CASE);
                        })
                    );
        }
        return singlesFound.get();
    }
    @Override
    public int checkLineShape(ClassicGrid sudokuGrid, String direction){
        String axis = Objects.equals(direction, "rows") ? "x" : "y";
        AtomicInteger singlesFound = new AtomicInteger();
        sudokuGrid.getLines().get(direction).stream()
                .filter(LineShape::hasOneEmptyCaseRemaining)
                .forEach(lineShape -> lineShape.getFirstEmptyCase()
                        .ifPresent(caseFound -> {
                            singlesFound.getAndIncrement();
                            caseFound.setValue(GridUtils.getFirstMissingNumberInShape(sudokuGrid.getWidth(), lineShape).orElseThrow());
                            caseFound.setCaseState(CaseState.FILLED_CASE);
                        })
                );
        return singlesFound.get();
    }
}
