package com.project.board.exceptions;

public class ColunaNotFoundException extends RuntimeException {
    public ColunaNotFoundException(String message) {
        super(message);
    }
}