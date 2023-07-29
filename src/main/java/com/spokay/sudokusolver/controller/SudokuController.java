package com.spokay.sudokusolver.controller;

import com.spokay.sudokusolver.builder.SudokuBuilder;
import com.spokay.sudokusolver.model.SudokuGame;
import com.spokay.sudokusolver.model.grid.GridCreationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/sudoku")
@AllArgsConstructor
public class SudokuController {

    SudokuBuilder sudokuBuilder;

    @PostMapping("/start")
    public ModelAndView startSudoku(@Validated GridCreationDTO gridCreationDTO) throws IOException {
        SudokuGame sudokuGame = sudokuBuilder.buildSudokuFromGridData(gridCreationDTO);
        return new ModelAndView("solved-sudoku").addObject(sudokuGame);
    }
}
