package com.supermarket.supermarket.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundError() {
        return "error404";
    }

    @ExceptionHandler(Exception.class)
    public String handleInternalServerError(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());

        return "error500";
    }
}
