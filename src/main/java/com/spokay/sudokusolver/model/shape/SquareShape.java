package com.spokay.sudokusolver.model.shape;

import com.spokay.sudokusolver.model.cases.Case;
import com.spokay.sudokusolver.model.cases.CaseState;
import com.spokay.sudokusolver.util.GridUtils;
import lombok.*;

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

    public boolean hasOnePossibilityRemaining(){
        List<Case> emptyCases = GridUtils.getAllCaseByType(CaseState.EMPTY_CASE, cases);

        return emptyCases.size() == 1;
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
}
