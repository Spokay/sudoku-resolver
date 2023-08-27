package com.spokay.sudokusolver.model.grid;

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
    public LineShape getLineByRowNumber(Integer rowNumber){
        return lines.get("row").get(rowNumber);
    }
    public LineShape getLineByColumnNumber(Integer columnNumber){
        return lines.get("column").get(columnNumber);
    }

    public Case getSpecificCase(Integer x, Integer y){
        return cases[y][x];
    }
    public ArrayList<Case> getAllCaseByType(String caseType, Case[][] allCases){
        ArrayList<Case> operatedCases = new ArrayList<>();
        Arrays.stream(allCases)
                .forEach(caseArr -> Arrays.stream(caseArr)
                        .filter(caseJ -> caseJ.getClass().getName().equals(caseType))
                        .forEach(operatedCases::add)
                );
        return operatedCases;
    }
}
