package com.example.reviewappv2.controllers;

import com.example.reviewappv2.dtos.request.LevelRequest;
import com.example.reviewappv2.dtos.response.LevelResponse;
import com.example.reviewappv2.exceptions.IllegalLevelCodeException;
import com.example.reviewappv2.exceptions.IllegalLevelPointsException;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.services.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/levels")
public class LevelController {

    private final LevelService levelService;

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<LevelResponse> save(
            @RequestBody LevelRequest levelRequest
    ) throws IllegalLevelCodeException, IllegalLevelPointsException {
        return new ResponseEntity<>(levelService.save(levelRequest), HttpStatus.OK);
    }

    @PutMapping("/{code}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<LevelResponse> update(
            @RequestBody LevelRequest levelRequest,
            @PathVariable int code
    ) throws NotFoundException {
        return new ResponseEntity<>(levelService.update(levelRequest, code), HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<String> delete(
            @PathVariable int code
    ) throws NotFoundException {
        levelService.delete(code);
        return new ResponseEntity<>("Level deleted with success", HttpStatus.OK);
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<LevelResponse> level(
            @PathVariable int code
    ) throws NotFoundException {
        return new ResponseEntity<>(levelService.findByCode(code), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> levels() {
        if(levelService.findAll().isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "No levels found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(levelService.findAll(), HttpStatus.OK);
    }

}
