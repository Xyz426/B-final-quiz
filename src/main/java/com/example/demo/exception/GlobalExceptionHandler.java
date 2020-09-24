package com.example.demo.exception;

import com.example.demo.error.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TraineeNotExistException.class)
    public ResponseEntity<ErrorResult> handleUserException(TraineeNotExistException e) {
        ErrorResult errorResult = new ErrorResult(HttpStatus.NOT_FOUND.toString(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(TrainerNotExistException.class)
    public ResponseEntity<ErrorResult> handleUserException(TrainerNotExistException e) {
        ErrorResult errorResult = new ErrorResult(HttpStatus.NOT_FOUND.toString(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(TrainerLessTwoException.class)
    public ResponseEntity<ErrorResult> handleUserException(TrainerLessTwoException e) {
        ErrorResult errorResult = new ErrorResult(HttpStatus.NOT_FOUND.toString(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.toString(),message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
