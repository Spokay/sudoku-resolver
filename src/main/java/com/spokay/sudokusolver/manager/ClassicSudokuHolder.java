package com.spokay.sudokusolver.manager;

import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class ClassicSudokuHolder {
    public List<ClassicSudokuContainer> sudokusHolded = new ArrayList<>();
    public Integer holdSudokuAndReturnId(ClassicSudokuGame sudokuGame){
        ClassicSudokuContainer sudokuContainer = ClassicSudokuContainer.builder()
                .sudokuHolded(sudokuGame)
                .build();
        sudokusHolded.add(sudokuContainer);
        Integer sudokuId = sudokusHolded.indexOf(sudokuContainer);
        sudokuContainer.setSudokuId(sudokuId);
        return sudokuId;
    }

    public ClassicSudokuGame getSudokuHoldedById(Integer id){
        return sudokusHolded.get(id).getSudokuHolded();
    }
}
