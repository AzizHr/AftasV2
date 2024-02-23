package com.example.reviewappv2.controllers;

import com.example.reviewappv2.dtos.request.CompetitionRequest;
import com.example.reviewappv2.dtos.response.CompetitionResponse;
import com.example.reviewappv2.exceptions.IllegalCompetitionDateException;
import com.example.reviewappv2.exceptions.IllegalCompetitionEndTimeException;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.services.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<CompetitionResponse> save(
            @RequestBody CompetitionRequest competitionRequest
            ) throws IllegalCompetitionEndTimeException, IllegalCompetitionDateException {
        return new ResponseEntity<>(competitionService.save(competitionRequest), HttpStatus.OK);
    }

    @PutMapping("/{code}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<CompetitionResponse> update(
            @RequestBody CompetitionRequest competitionRequest,
            @PathVariable String code
    ) throws IllegalCompetitionEndTimeException, IllegalCompetitionDateException, NotFoundException {
        return new ResponseEntity<>(competitionService.update(competitionRequest, code), HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<String> delete(
            @PathVariable String code
    ) throws NotFoundException {
        competitionService.delete(code);
        return new ResponseEntity<>("Competition deleted with success", HttpStatus.OK);
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<CompetitionResponse> competition(
            @PathVariable String code
    ) throws NotFoundException {
        return new ResponseEntity<>(competitionService.findByCode(code), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> competitions(Pageable pageable) {
        if(competitionService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "No competitions found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(competitionService.findAll(pageable), HttpStatus.OK);
    }

}
