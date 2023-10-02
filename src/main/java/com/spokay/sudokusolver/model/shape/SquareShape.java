package com.spokay.sudokusolver.model.shape;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.cases.CaseState;
import com.spokay.sudokusolver.util.GridUtils;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SquareShape implements Shape{
    Case[][] cases;

    @Override
    public boolean hasOneEmptyCaseRemaining() {
        List<Case> emptyCases = GridUtils.getAllCaseByType(CaseState.EMPTY_CASE, cases);

        return emptyCases.size() == 1;
    }

    @Override
    public boolean hasSinglesPossibilitiesRemaining() {
        AtomicBoolean hasSinglePossibilities = new AtomicBoolean();
        Arrays.stream(cases).forEach(caseRow -> Arrays.stream(caseRow)
                .filter(caseInRow -> caseInRow.getPossibleValue().size() == 1)
                .forEach(singlePossibilityFound -> hasSinglePossibilities.set(true))
        );
        return hasSinglePossibilities.get();
    }

    public Optional<Case> getFirstEmptyCase() {
        List<Case> emptyCases = GridUtils.getAllCaseByType(CaseState.EMPTY_CASE, cases);
        return emptyCases.stream().findFirst();
    }

    public boolean containsNumber(Integer numberToCheck) {
        AtomicBoolean containsNumber = new AtomicBoolean();

        Arrays.stream(cases).forEach(caseRow -> Arrays.stream(caseRow)
                .filter(caseInRow -> caseInRow.getValue().equals(numberToCheck))
                .forEach(caseInRow -> containsNumber.set(true))
        );
        return containsNumber.get();
    }

    public List<Case> getSinglePossibilitiesCase() {
        List<Case> singlePossibilitiesCases = new ArrayList<>();
        Arrays.stream(cases).forEach(caseRow -> Arrays.stream(caseRow)
                .filter(caseInRow -> caseInRow.getPossibleValue().size() == 1)
                .forEach(singlePossibilitiesCases::add)
        );
        return singlePossibilitiesCases;
    }
}
