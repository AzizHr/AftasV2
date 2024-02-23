package com.example.reviewappv2.controllers;

import com.example.reviewappv2.dtos.request.FishRequest;
import com.example.reviewappv2.dtos.response.FishResponse;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.services.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fish")
public class FishController {

    private final FishService fishService;

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<FishResponse> save(
            @RequestBody FishRequest fishRequest
    ) throws NotFoundException {
        return new ResponseEntity<>(fishService.save(fishRequest), HttpStatus.OK);
    }

    @PutMapping("/{name}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<FishResponse> update(
            @RequestBody FishRequest fishRequest,
            @PathVariable String name
    ) throws NotFoundException {
        return new ResponseEntity<>(fishService.update(fishRequest, name), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<String> delete(
            @PathVariable String name
    ) throws NotFoundException {
        fishService.delete(name);
        return new ResponseEntity<>("Fish deleted with success", HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<FishResponse> fish(
            @PathVariable String name
    ) throws NotFoundException {
        return new ResponseEntity<>(fishService.findByName(name), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> fishList() {
        if(fishService.findAll().isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "No fish found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(fishService.findAll(), HttpStatus.OK);
    }

}
