package com.spokay.sudokusolver.manager.checks;

import com.spokay.sudokusolver.model.cases.CaseState;
import com.spokay.sudokusolver.model.grid.ClassicGrid;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.shape.SquareShape;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SinglePossibilityChecker implements Checker{

    @Override
    public int checkAllShapeType(ClassicGrid sudokuGrid) {
        int singleInRowResult = checkLineShape(sudokuGrid, "rows");
        int singleInColumnResult = checkLineShape(sudokuGrid, "columns");

        int singleInSquares = checkSquareShape(sudokuGrid);
        return singleInRowResult + singleInColumnResult + singleInSquares;
    }
    @Override
    public int checkLineShape(ClassicGrid sudokuGrid, String direction) {
        AtomicInteger singlesFound = new AtomicInteger();
        sudokuGrid.getLines().get(direction).stream()
                .filter(LineShape::hasSinglesPossibilitiesRemaining)
                .forEach(lineShape -> lineShape.getSinglePossibilitiesCase().forEach(caseToFill -> {
                    caseToFill.setValue(caseToFill.getLastPossibility());
                    caseToFill.setCaseState(CaseState.FILLED_CASE);
                    caseToFill.getPossibleValue().clear();
                    singlesFound.getAndIncrement();
                }));
        return singlesFound.get();
    }

    @Override
    public int checkSquareShape(ClassicGrid sudokuGrid){
        AtomicInteger singlesFound = new AtomicInteger();
        for (int row = 0; row < sudokuGrid.getSquares().size(); row++) {
            sudokuGrid.getSquares().get(row)
                    .stream()
                    .filter(SquareShape::hasSinglesPossibilitiesRemaining)
                    .forEach(squareInRow -> squareInRow.getSinglePossibilitiesCase()
                        .forEach(caseToFill -> {
                            caseToFill.setValue(caseToFill.getLastPossibility());
                            caseToFill.setCaseState(CaseState.FILLED_CASE);
                            caseToFill.getPossibleValue().clear();
                            singlesFound.getAndIncrement();
                        })
                    );
        }


        return singlesFound.get();
    }
}
