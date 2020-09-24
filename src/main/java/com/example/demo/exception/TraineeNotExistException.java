package com.example.demo.exception;

public class TraineeNotExistException extends RuntimeException{
    public TraineeNotExistException(String message) {
        super(message);
    }
}
