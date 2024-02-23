package com.example.reviewappv2.handlers;

import com.example.reviewappv2.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IllegalCompetitionDateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalCompetitionDateException(IllegalCompetitionDateException e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IllegalCompetitionEndTimeException.class)
    public ResponseEntity<Map<String, String>> handleIllegalCompetitionEndTimeException(IllegalCompetitionEndTimeException e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IllegalLevelCodeException.class)
    public ResponseEntity<Map<String, String>> handleIllegalLevelCodeException(IllegalLevelCodeException e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IllegalLevelPointsException.class)
    public ResponseEntity<Map<String, String>> handleIllegalLevelPointsException(IllegalLevelPointsException e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotAMemberException.class)
    public ResponseEntity<Map<String, String>> handleNotAMemberException(NotAMemberException e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

}
