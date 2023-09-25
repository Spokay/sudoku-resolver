package com.spokay.sudokusolver.model.grid;

import com.spokay.sudokusolver.builder.CaseBuilder;
import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.cases.EmptyCase;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.shape.SquareShape;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClassicGrid implements Grid{
    private HashMap<Integer, List<SquareShape>> squares;
    private HashMap<String, List<LineShape>> lines;
    private Case[][] cases;
    private Integer width;
    private Integer height;
    private Integer squareSize;
    public LineShape getLineByRowNumber(Integer rowNumber){
        return lines.get("rows").get(rowNumber);
    }
    public LineShape getLineByColumnNumber(Integer columnNumber){
        return lines.get("columns").get(columnNumber);
    }

    public Case getSpecificCase(Integer x, Integer y){
        return cases[y][x];
    }
}
