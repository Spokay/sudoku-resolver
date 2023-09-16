package com.spokay.sudokusolver.model.cases;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public abstract class Case {
    protected Integer value;
    protected int[] possibleValue;
    protected HashMap<String, Integer> coords = new HashMap<>();

    public Case(HashMap<String, Integer> coords){
        this.setCoords(coords);
    }
}
