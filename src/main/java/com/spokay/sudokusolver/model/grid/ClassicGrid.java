package com.spokay.sudokusolver.model.grid;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.shape.SquareShape;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassicGrid implements Grid{
    private List<SquareShape> squares;
    private HashMap<String, List<LineShape>> lines;
    private Case[][] cases;
    public LineShape getLineByRowNumber(Integer rowNumber){
        return lines.get("row").get(rowNumber);
    }
    public LineShape getLineByColumnNumber(Integer columnNumber){
        return lines.get("column").get(columnNumber);
    }

    public Case getSpecificCase(Integer x, Integer y){
        return cases[y][x];
    }
}
