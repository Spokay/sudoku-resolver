package com.spokay.sudokusolver.util;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.cases.EmptyCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GridUtils {
    public static ArrayList<Case> getAllCaseByType(String caseType, Case[][] allCases){
        ArrayList<Case> operatedCases = new ArrayList<>();
        Arrays.stream(allCases)
                .forEach(caseArr -> Arrays.stream(caseArr)
                        .filter(caseJ -> caseJ.getClass().getName().equals(caseType))
                        .forEach(operatedCases::add)
                );
        return operatedCases;
    }

    public static HashMap<Integer, Integer> getAmountsOfValuesForEachNumbers(Case[][] cases){
        HashMap<Integer, Integer> valuesAmounts = new HashMap<>();

        for (int i = 1; i < cases.length + 1; i++) {
            int finalI = i;
            AtomicInteger counter = new AtomicInteger();
            Arrays.stream(cases)
                    .forEach(caseArr -> Arrays.stream(caseArr)
                        .filter(caseJ -> !caseJ.getClass().equals(EmptyCase.class) && caseJ.getValue() == finalI)
                        .forEach(caseJ -> counter.getAndIncrement())
                );

            valuesAmounts.put(i, counter.get());
        }
        return valuesAmounts;
    }
}
