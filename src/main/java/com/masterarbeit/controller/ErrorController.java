package com.masterarbeit.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // do something
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
