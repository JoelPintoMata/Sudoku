package com.model;

import spark.utils.Assert;

/**
 * Sudoku board game test suite
 */
public class SudokuBoardTest {

    @org.junit.Test
    public void isValid() throws Exception {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.generate(0);
        Assert.isTrue(sudokuBoard.isValid("0","0").isEmpty(), "Line 0 column 0 must be valid");

        sudokuBoard.play("0", "0", "0");
        Assert.isTrue(!sudokuBoard.isValid("0","0").isEmpty(), "Line 0 column 0 must not be valid");

        sudokuBoard = new SudokuBoard();
        sudokuBoard.generate(81);

        sudokuBoard.play("0","0","1");
        sudokuBoard.play("0","1","2");
        sudokuBoard.play("0","2","3");
        sudokuBoard.play("0","3","7");
        sudokuBoard.play("0","4","8");
        sudokuBoard.play("0","5","9");
        sudokuBoard.play("0","6","4");
        sudokuBoard.play("0","7","5");
        sudokuBoard.play("0","8","6");
        Assert.isTrue(sudokuBoard.isValid("0","0").equals("Invalid column.\nInvalid section.\n"), "Line 0 column 0 should have a valid line but an invalid column and section");

        sudokuBoard.play("0","0","1");
        sudokuBoard.play("1","0","2");
        sudokuBoard.play("2","0","3");
        sudokuBoard.play("3","0","7");
        sudokuBoard.play("4","0","8");
        sudokuBoard.play("5","0","9");
        sudokuBoard.play("6","0","4");
        sudokuBoard.play("7","0","5");
        sudokuBoard.play("8","0","6");
        Assert.isTrue(sudokuBoard.isValid("0","0").equals("Invalid section.\n"), "Line 0 column 0 should have a valid line and column but an invalid section");

        sudokuBoard = new SudokuBoard();
        sudokuBoard.generate(81);

        sudokuBoard.play("0","0","1");
        sudokuBoard.play("0","1","2");
        sudokuBoard.play("0","2","3");
        sudokuBoard.play("1","0","7");
        sudokuBoard.play("1","1","8");
        sudokuBoard.play("1","2","9");
        sudokuBoard.play("2","0","4");
        sudokuBoard.play("2","1","5");
        sudokuBoard.play("2","2","6");
        Assert.isTrue(sudokuBoard.isValid("0","0").equals("Invalid line.\nInvalid column.\n"), "Line 0 column 0 should have a section but an invalid line and column");
    }

    @org.junit.Test
    public void isGameWon() throws Exception {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.generate(81);

        sudokuBoard.play("0","0","1");
        sudokuBoard.play("0","1","2");
        sudokuBoard.play("0","2","3");
        sudokuBoard.play("0","3","7");
        sudokuBoard.play("0","4","8");
        sudokuBoard.play("0","5","9");
        sudokuBoard.play("0","6","4");
        sudokuBoard.play("0","7","5");
        sudokuBoard.play("0","8","6");

        sudokuBoard.play("1","0","4");
        sudokuBoard.play("1","1","5");
        sudokuBoard.play("1","2","6");
        sudokuBoard.play("1","3","1");
        sudokuBoard.play("1","4","2");
        sudokuBoard.play("1","5","3");
        sudokuBoard.play("1","6","7");
        sudokuBoard.play("1","7","8");
        sudokuBoard.play("1","8","9");

        sudokuBoard.play("2","0","7");
        sudokuBoard.play("2","1","8");
        sudokuBoard.play("2","2","9");
        sudokuBoard.play("2","3","4");
        sudokuBoard.play("2","4","5");
        sudokuBoard.play("2","5","6");
        sudokuBoard.play("2","6","1");
        sudokuBoard.play("2","7","2");
        sudokuBoard.play("2","8","3");

        sudokuBoard.play("3","0","2");
        sudokuBoard.play("3","1","3");
        sudokuBoard.play("3","2","1");
        sudokuBoard.play("3","3","8");
        sudokuBoard.play("3","4","9");
        sudokuBoard.play("3","5","7");
        sudokuBoard.play("3","6","5");
        sudokuBoard.play("3","7","6");
        sudokuBoard.play("3","8","4");

        sudokuBoard.play("4","0","5");
        sudokuBoard.play("4","1","6");
        sudokuBoard.play("4","2","4");
        sudokuBoard.play("4","3","2");
        sudokuBoard.play("4","4","3");
        sudokuBoard.play("4","5","1");
        sudokuBoard.play("4","6","8");
        sudokuBoard.play("4","7","9");
        sudokuBoard.play("4","8","7");

        sudokuBoard.play("5","0","8");
        sudokuBoard.play("5","1","9");
        sudokuBoard.play("5","2","7");
        sudokuBoard.play("5","3","5");
        sudokuBoard.play("5","4","6");
        sudokuBoard.play("5","5","4");
        sudokuBoard.play("5","6","2");
        sudokuBoard.play("5","7","3");
        sudokuBoard.play("5","8","1");

        sudokuBoard.play("6","0","3");
        sudokuBoard.play("6","1","1");
        sudokuBoard.play("6","2","2");
        sudokuBoard.play("6","3","9");
        sudokuBoard.play("6","4","7");
        sudokuBoard.play("6","5","8");
        sudokuBoard.play("6","6","6");
        sudokuBoard.play("6","7","4");
        sudokuBoard.play("6","8","5");

        sudokuBoard.play("7","0","6");
        sudokuBoard.play("7","1","4");
        sudokuBoard.play("7","2","5");
        sudokuBoard.play("7","3","3");
        sudokuBoard.play("7","4","1");
        sudokuBoard.play("7","5","2");
        sudokuBoard.play("7","6","9");
        sudokuBoard.play("7","7","7");
        sudokuBoard.play("7","8","8");

        sudokuBoard.play("8","0","9");
        sudokuBoard.play("8","1","7");
        sudokuBoard.play("8","2","8");
        sudokuBoard.play("8","3","6");
        sudokuBoard.play("8","4","4");
        sudokuBoard.play("8","5","5");
        sudokuBoard.play("8","6","3");
        sudokuBoard.play("8","7","1");
        Assert.isTrue(!sudokuBoard.isGameWon(), "The game is not over yet");

        sudokuBoard.play("8","8","2");
        Assert.isTrue(sudokuBoard.isGameWon(), "The game must be over now");
    }
}