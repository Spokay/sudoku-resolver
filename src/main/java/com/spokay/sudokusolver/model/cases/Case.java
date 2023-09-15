package com.spokay.sudokusolver.model.cases;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Case {
    protected Integer value;
    protected int[] possibleValue;
}
