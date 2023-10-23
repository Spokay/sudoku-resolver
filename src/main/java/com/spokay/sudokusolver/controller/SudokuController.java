package com.spokay.sudokusolver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spokay.sudokusolver.builder.ClassicSudokuBuilder;
import com.spokay.sudokusolver.manager.ClassicSudokuGameManager;
import com.spokay.sudokusolver.manager.ClassicSudokuHolder;
import com.spokay.sudokusolver.model.grid.GridCreationDTO;
import com.spokay.sudokusolver.model.shape.LineShape;
import com.spokay.sudokusolver.model.sudokugame.ClassicSudokuGame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sudoku")
@AllArgsConstructor
public class SudokuController {

    ClassicSudokuBuilder classicSudokuBuilder;

    ObjectMapper objectMapper;

    ClassicSudokuHolder sudokuHolder;

    ClassicSudokuGameManager sudokuGameManager;

    @GetMapping("/{id}")
    public ModelAndView displaySudoku(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        ClassicSudokuGame sudokuGame = sudokuHolder.getSudokuHoldedById(id);

        List<LineShape> sudokuRows = sudokuGame.getGrid().getLines().get("rows");
        Integer squareSize = sudokuGame.getGrid().getSquareSize();
        Map<String, Object> data = new HashMap<>();
        data.put("sudokuRows", sudokuRows);
        data.put("squareSize", squareSize);
        data.put("sudokuId", id);
        if (redirectAttributes.getFlashAttributes().containsKey("result")){
            data.put("result", redirectAttributes.getFlashAttributes().get("result"));
        }

        return new ModelAndView("sudoku-viewer.html")
                .addAllObjects(data);
    }

    @PostMapping("/start")
    public ModelAndView startSudoku(@Validated GridCreationDTO gridCreationDTO) throws IOException {
        ClassicSudokuGame sudokuGame = classicSudokuBuilder.buildSudokuFromGridData(gridCreationDTO);
        Integer sudokuId = sudokuHolder.holdSudokuAndReturnId(sudokuGame);
        return new ModelAndView("redirect:/sudoku/"+ sudokuId);
    }

    @GetMapping("/{id}/next-turn")
    public ModelAndView nextTurn(@PathVariable Integer id) {
        ClassicSudokuGame sudokuGame = sudokuHolder.getSudokuHoldedById(id);
        sudokuGameManager.nextTurn(sudokuGame);

        return new ModelAndView("redirect:/sudoku/"+ id);
    }

    @GetMapping("/{id}/resolve-all")
    ModelAndView resolveAll(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        ClassicSudokuGame sudokuGame = sudokuHolder.getSudokuHoldedById(id);
        boolean result = sudokuGameManager.resolveAllCases(sudokuGame);
        redirectAttributes.addFlashAttribute("result", result);
        return new ModelAndView("redirect:/sudoku/" + id).addObject("result", result);
    }
}
