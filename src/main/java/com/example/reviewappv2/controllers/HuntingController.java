package com.example.reviewappv2.controllers;

import com.example.reviewappv2.dtos.request.HuntingRequest;
import com.example.reviewappv2.dtos.response.HuntingResponse;
import com.example.reviewappv2.exceptions.NotAMemberException;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.services.HuntingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/huntings")
public class HuntingController {

    private final HuntingService huntingService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<Map<String, Integer>> save(
            @RequestBody HuntingRequest huntingRequest
    ) throws NotFoundException, NotAMemberException {
        return new ResponseEntity<>(Map.of("score", huntingService.save(huntingRequest)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<Map<String, Integer>> update(
            @RequestBody HuntingRequest huntingRequest,
            @PathVariable int id
    ) throws NotFoundException {
        return new ResponseEntity<>(Map.of("score", huntingService.update(huntingRequest, id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<String> delete(
            @PathVariable int id
    ) throws NotFoundException {
        huntingService.delete(id);
        return new ResponseEntity<>("Hunting deleted with success", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<HuntingResponse> hunting(
            @PathVariable int id
    ) throws NotFoundException {
        return new ResponseEntity<>(huntingService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<?> huntings() {
        if(huntingService.findAll().isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "No huntings found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(huntingService.findAll(), HttpStatus.OK);
    }

}
