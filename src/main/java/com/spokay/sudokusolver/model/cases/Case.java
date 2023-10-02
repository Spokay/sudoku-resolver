package com.spokay.sudokusolver.model.cases;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Set;

@Getter
@Setter
@Builder
public class Case {
    public CaseState caseState;
    private Integer value;
    private Set<Integer> possibleValue;
    private HashMap<String, Integer> coords;

    public boolean isEmpty(){
        return caseState.equals(CaseState.EMPTY_CASE);
    }
    public int getLastPossibility(){
        return possibleValue.stream().findFirst().orElseThrow();
    }
    @Override
    public String toString() {
        return caseState.toString().concat(" "+getValue());
    }
}
