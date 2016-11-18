package com;


import com.controller.SudokuBoardController;
import com.controller.SudokuBoardService;

public class Main {

    public static void main(String[] args) {
        new SudokuBoardController(new SudokuBoardService());
    }
}