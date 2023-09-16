package com.spokay.sudokusolver.model.cases;

import java.util.HashMap;

public class FilledCase extends Case{
    public FilledCase(HashMap<String, Integer> coords, Integer value){
        super(coords);
        this.setValue(value);
    }
}
