package com.model;


import java.util.Random;

/**
 * A sudoku game board concrete implementation
 */
public class SudokuBoard implements GameBoard {

//    the number of cells of this board game
    private static final int NUMBER_OF_CELLS = 81;

//    this sudoku game difficulty level
    private int difficulty_level = 20;
//    this sudoku board array containing the values per line//column
    private int[][] sudokuBoardArray = new int[9][9];
//    the number of played (filled) positions
    private int played;

    /**
     * Returns a sudoku board game array
     * @return
     */
    public int[][] getSudokuBoardArray(){
        return sudokuBoardArray;
    }
    /**
     * Generates sudoku game board
     */
    @Override
    public int[][] generate(int difficultyLevel){
        Random rand = new Random();
        int line;
        int column;
        generateAux();

        if(difficultyLevel == -1)
            difficultyLevel = difficulty_level;

//        randomly remove values from the complete sudoku game board
        played = NUMBER_OF_CELLS - difficultyLevel;
        int i = 0;
        while(i != difficultyLevel){
            line = rand.nextInt(9);
            column = rand.nextInt(9);
            if(sudokuBoardArray[line][column] != 0) {
                sudokuBoardArray[line][column] = 0;
                i++;
            }
        }
        return sudokuBoardArray;
    }

    /**
     * Generates sudoku game board
     */
    private void generateAux(){
        int k=1,n=1;
        for(int i=0;i<9;i++){
            k=n;
            for(int j=0;j<9;j++){
                if(k<=9){
                    sudokuBoardArray[i][j]=k;
                    k++;
                }else{
                    k=1;
                    sudokuBoardArray[i][j]=k;
                    k++;
                }
            }
            n=k+3;
            if(k==10)
                n=4;
            if(n>9)
                n=(n%9)+1;
        }
        random_gen(1);
        random_gen(0);
    }

    /**
     * Performs a random number of permutations on the generated sudoku board
     * @param check
     */
    private void random_gen(int check){
        int k1,k2,max = 2,min = 0;
        Random r = new Random();
        for(int i=0; i<3; i++){
            k1 = r.nextInt(max-min+1)+min;
            do{
                k2 = r.nextInt(max-min+1)+min;
            }
            while(k1==k2);
            max+=3;
            min+=3;
            if(check==1)
                permutation_row(k1,k2);
            else
                if(check==0)
                    permutation_col(k1,k2);
        }
    }

    private void permutation_row(int k1,int k2){
        int temp;
        for(int j=0;j<9;j++){
            temp = sudokuBoardArray[k1][j];
            sudokuBoardArray[k1][j] = sudokuBoardArray[k2][j];
            sudokuBoardArray[k2][j] = temp;
        }
    }

    private void permutation_col(int k1,int k2){
        int temp;
        for(int j=0;j<9;j++){
            temp = sudokuBoardArray[j][k1];
            sudokuBoardArray[j][k1] = sudokuBoardArray[j][k2];
            sudokuBoardArray[j][k2] = temp;
        }
    }

    /**
     * Generates sudokuBoardArray string with the sudoku board
     * @return the sudoku board
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("*-*-*-*-*-*-*-*-*-*\n");
        for(int i=0; i<9; i++) {
            result.append("*");
            for (int j = 0; j < 9; j++){
                result.append(sudokuBoardArray[i][j]);
                result.append("*");
            }
            result.append("\n");
        }
        result.append("*-*-*-*-*-*-*-*-*-*\n");
        return result.toString();
    }

    /**
     * Checks if the total of sudokuBoardArray line is valid (equals to 45)
     * @param line, line number
     * @return
     */
    private boolean verifyLine(int line){
        int result = 0;
        for(int j=0; j<9; j++)
            result = result + sudokuBoardArray[line][j];
        if (result == 45)
            return true;
        return false;
    }

    /**
     * Checks if the total of sudokuBoardArray column is valid (equals to 45)
     * @param column, column number
     * @return
     */
    private boolean verifyColumn(int column){
        int result = 0;
        for(int j=0; j<9; j++)
            result = result + sudokuBoardArray[j][column];
        if (result == 45)
            return true;
        return false;
    }

    /**
     * Checks if the total of sudokuBoardArray 3x3 section is valid
     * @param column, column number
     * @return
     */
    private boolean verifySection(int line, int column){
        int result = 0;
        if (line >= 0 && line <= 2)
            line = 0;
        if (line >= 3 && line <= 5)
            line = 3;
        if (line >= 6 && line <= 8)
            line = 6;
        if (column >= 0 && column <= 2)
            column = 0;
        if (column >= 3 && column <= 5)
            column = 3;
        if (column >= 6 && column <= 8)
            column = 6;
        for(int i=line; i < line+3; i++)
            for(int j=column; j < column+3; j++)
                result = result + sudokuBoardArray[i][j];
        if (result == 45)
            return true;
        return false;
    }

    /**
     * Checks if the current play is valid
     * @param line, line number
     * @param column, column number
     * @return
     */
    @Override
    public String isValid(String line, String column) {
        StringBuilder result = new StringBuilder();
        if(!verifyLine(Integer.parseInt(line)))
            result.append("Invalid line.\n");
        if(!verifyColumn(Integer.parseInt(column)))
            result.append("Invalid column.\n");
        if(!verifySection(Integer.parseInt(line), Integer.parseInt(column)))
            result.append("Invalid section.\n");
        return result.toString();
    }

    /**
     * Sets the played value in this sudoku board
     * @param line, line number
     * @param column, column number
     * @param value, the value to insert in the given line and column
     */
    @Override
    public void play(String line, String column, String value) {
        if(sudokuBoardArray[Integer.parseInt(line)][Integer.parseInt(column)] == 0)
            played++;
        sudokuBoardArray[Integer.parseInt(line)][Integer.parseInt(column)] = Integer.parseInt(value);
    }

    /**
     * Checks if the game was won
     * @return
     */
    public boolean isGameWon() {
        return played == NUMBER_OF_CELLS;
    }
}
