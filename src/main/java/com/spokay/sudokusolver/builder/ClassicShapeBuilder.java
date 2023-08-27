package com.spokay.sudokusolver.builder;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.shape.SquareShape;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ClassicShapeBuilder {
    public HashMap<String, List<LineShape>> buildLinesFromCases(Case[][] cases) {
        HashMap<String, List<LineShape>> lines = new HashMap<>();
        List<LineShape> rowsLines = new ArrayList<>();
        List<LineShape> columnLines = new ArrayList<>();
        // initialize rows array and fill all the rows
        for (int y = 0; y < cases.length; y++) {
            Case[] casesInRow = new Case[cases.length];
            System.arraycopy(cases[y], 0, casesInRow, 0, cases[y].length);

            rowsLines.add(y,
                    LineShape.builder()
                    .lineCases(casesInRow)
                    .build()
            );
        }

        // initialize column array
        for (int y = 0; y < cases.length; y++) {
            Case[] casesInColumn = new Case[cases.length];
            columnLines.add(y,
                    LineShape.builder()
                    .lineCases(casesInColumn)
                    .build()
            );
        }
        // fill all the columns
        for (int y = 0; y < cases.length; y++) {
             for (int x = 0; x < cases[y].length; x++) {
                columnLines.get(x).getLineCases()[y] = cases[y][x];
            }
        }

        // fill the lines HashMap
        lines.put("rows", rowsLines);
        lines.put("columns", columnLines);
        return lines;
    }

    public HashMap<Integer, List<SquareShape>> buildSquaresFromCases(Case[][] cases, Integer squareSize) {
        HashMap<Integer, List<SquareShape>> squares = new HashMap<>();

        int squaresPerRows = cases.length / squareSize;
        // initialize the correct number of SquaresShape
        for (int rowGroupIndex = 0; rowGroupIndex < squaresPerRows; rowGroupIndex++) {
            List<SquareShape> squaresShapesInRowGroup = new ArrayList<>();

            for (int columnGroupIndex = 0; columnGroupIndex < squaresPerRows; columnGroupIndex++) {
                // initialize the cases of the SquareShape
                Case[][] casesInSquare = new Case[squareSize][squareSize];
                SquareShape squareShape = SquareShape.builder().cases(casesInSquare).build();
                squaresShapesInRowGroup.add(squareShape);
                // define where the array will start to be copied
                int rowStart = rowGroupIndex == 0 ? 0 : rowGroupIndex == 1 ? squareSize : squareSize*2;
                int columnStart = columnGroupIndex == 0 ? 0 : columnGroupIndex == 1 ? squareSize : squareSize*2;
                // copy the cases to the casesInSquare array starting from rowStart and columnStart to square size length
                fillCasesInSquare(cases, rowStart, columnStart, casesInSquare, squareSize);
            }
            squares.put(rowGroupIndex, squaresShapesInRowGroup);
        }

        return squares;
    }

    private void fillCasesInSquare(Case[][] cases, int rowStart, int columnStart, Case[][] casesInSquare, int squareSize){
        for (int j = 0; j < squareSize; j++){
            System.arraycopy(cases[rowStart], columnStart, casesInSquare[j], 0, squareSize);
            rowStart++;
        }
    }
}
