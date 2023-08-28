package com.spokay.sudokusolver.controller;

import com.spokay.sudokusolver.builder.ClassicSudokuBuilder;
import com.spokay.sudokusolver.model.grid.GridCreationDTO;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/sudoku")
@AllArgsConstructor
public class SudokuController {

    ClassicSudokuBuilder classicSudokuBuilder;

    @PostMapping("/start")
    public ModelAndView startSudoku(@Validated GridCreationDTO gridCreationDTO) throws IOException {
        ClassicSudokuGame sudokuGame = classicSudokuBuilder.buildSudokuFromGridData(gridCreationDTO);
        List<LineShape> sudokuRows = sudokuGame.getGrid().getLines().get("rows");
        return new ModelAndView("sudoku-viewer").addObject("sudokuRows",sudokuRows);
    }
}
