package com.controller;


import com.model.SudokuBoard;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

/**
 * Sudoku board game service class
 */
public class SudokuBoardService {

    Map<String, SudokuBoard> sudokuBoardMap = new HashMap<>();

    /**
     * Generates a new sudoku board game with a given difficulty level (number of empty cells)
     * @param req
     * @param res
     * @return
     */
    public ModelAndView generate(Request req, Response res) {
        String difficultyLevel = Optional.ofNullable(req.queryParams("level")).orElse("-1");

        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.generate(Integer.parseInt(difficultyLevel));
        Map<String, Object> params = new HashMap<>();

        String sudokuBoardId = req.session().id();

        sudokuBoardMap.put(sudokuBoardId, sudokuBoard);

        params.put("sudokuBoardId", sudokuBoardId);
        params.put("sudokuBoardArray", sudokuBoard.getSudokuBoardArray());
        return new ModelAndView(params, "sudoku-template");
    }

    /**
     * Checks if a play is valid and the game was own
     * @param req
     * @param res
     * @return
     */
    public String check(Request req, Response res) {
        String id = req.queryParams("sudokuBoardId");
        String lineColumn = req.queryParams("lineColumn");

        SudokuBoard sudokuBoard = sudokuBoardMap.get(id);
        String result = sudokuBoard.isValid(lineColumn.substring(0,1), lineColumn.substring(1,2));
        if(result.isEmpty() && sudokuBoard.isGameWon())
            result = "You won!";
        return result;
    }

    /**
     * Sets a value into the sudoku board game
     * @param req
     * @param res
     */
    public void play(Request req, Response res) {
        String sudokuBoardId = req.queryParams("sudokuBoardId");
        String lineColumn = req.queryParams("lineColumn");
        String value = req.queryParams("value");

        SudokuBoard sudokuBoard = sudokuBoardMap.get(sudokuBoardId);
        sudokuBoard.play(lineColumn.substring(0,1), lineColumn.substring(1,2), value);
    }
}