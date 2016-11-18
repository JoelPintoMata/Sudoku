package com.controller;


import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;

public class SudokuBoardController {

    public SudokuBoardController(SudokuBoardService sudokuBoardService) {
        get("/", sudokuBoardService::generate, new ThymeleafTemplateEngine());
        get("/generate", sudokuBoardService::generate, new ThymeleafTemplateEngine());
        post("/check", (req, res) -> {
            sudokuBoardService.play(req, res);
            return sudokuBoardService.check(req, res);
        });
    }
}