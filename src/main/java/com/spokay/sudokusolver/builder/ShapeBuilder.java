package com.spokay.sudokusolver.builder;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.shape.SquareShape;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class ShapeBuilder {
    public HashMap<String, List<LineShape>> buildLinesFromCases(Case[][] cases) {
        HashMap<String, List<LineShape>> lines = new HashMap<>();
        List<LineShape> xLines = new ArrayList<>();
        List<LineShape> yLines = new ArrayList<>();
        // get all the line on x-axis

        for (int y = 0; y < cases.length; y++) {
            Case[] casesInLine = new Case[cases.length];
            System.arraycopy(cases[y], 0, casesInLine, 0, cases[y].length);
            xLines.add(y,
                    LineShape.builder()
                    .lineCases(casesInLine)
                    .build()
            );
        }

        Arrays.stream(xLines.get(8).getLineCases()).forEach(e -> System.out.println(e.getValue() + "\n"));
        return null;
    }

    public List<SquareShape> buildSquaresFromCases(Case[][] cases) {
        return null;
    }
}
