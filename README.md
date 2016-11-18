Initial description
===================

1. Make a sudoku generator that generates a sudoku playing board in a browser, like the right image below, with some cells empty, some filled.
2. Refreshing the webpage should generate a new board, prefilled like above. A webform or ajax will be needed tos end the filled in answeres to the server to check for correct answers.
3. Develop the checking functions to check if the filled in numbers are correct, show the user directly and filling in a number if it’s correct or not. Check the full table at once is ok too.
4. Extra: Make a virtual player that with 1 button click fills the table with correct answers.

Implementation
==============

### Endpoint:
Default endpoint generating a new sudoku board game with the default difficulty level
```
http://localhost:4567
```
Generating a new sudoku board game with the given difficulty level
```
http://localhost:4567/generate?level=<difficulty_level>
```
Difficulty level:
[0..81] the number of empty cells

Usage
=====

Run
---
```
```

Test
----
```
$ mvn test
```
