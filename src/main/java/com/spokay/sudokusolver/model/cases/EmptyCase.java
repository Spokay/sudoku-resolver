package com.spokay.sudokusolver.model.cases;

import java.util.HashMap;

public class EmptyCase extends Case{

    public EmptyCase(HashMap<String, Integer> coords){
        super(coords);
        this.setValue(0);
    }
}
