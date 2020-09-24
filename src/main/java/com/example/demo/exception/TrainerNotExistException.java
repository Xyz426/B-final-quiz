package com.example.demo.exception;

public class TrainerNotExistException extends RuntimeException{
    public TrainerNotExistException(String message) {
        super(message);
    }
}
