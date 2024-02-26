package com.example.reviewappv2.controllers;

import com.example.reviewappv2.dtos.request.RankingRequest;
import com.example.reviewappv2.dtos.response.RankingResponse;
import com.example.reviewappv2.exceptions.AlreadyAMemberException;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.services.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rankings")
public class RankingController {

    private final RankingService rankingService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<RankingResponse> save(
            @RequestBody RankingRequest rankingRequest
    ) throws NotFoundException, AlreadyAMemberException {
        return new ResponseEntity<>(rankingService.save(rankingRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<RankingResponse> update(
            @RequestBody RankingRequest rankingRequest,
            @PathVariable int id
    ) throws NotFoundException {
        return new ResponseEntity<>(rankingService.update(rankingRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<String> delete(
            @PathVariable int id
    ) throws NotFoundException {
        rankingService.delete(id);
        return new ResponseEntity<>("Ranking deleted with success", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<RankingResponse> ranking(
            @PathVariable int id
    ) throws NotFoundException {
        return new ResponseEntity<>(rankingService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<?> rankings() {
        if(rankingService.findAll().isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "No rankings found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(rankingService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-competition/{code}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'JURY')")
    public ResponseEntity<?> rankingsByCompetitionCode(@PathVariable String code) {
        if(rankingService.findAllByCompetitionCode(code).isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "No rankings found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(rankingService.findAllByCompetitionCode(code), HttpStatus.OK);
    }

    @GetMapping("/podium/{competitionCode}")
    public ResponseEntity<List<RankingResponse>> podium(@PathVariable String competitionCode) {
        return new ResponseEntity<>(rankingService.findTop3(competitionCode), HttpStatus.OK);
    }

}
