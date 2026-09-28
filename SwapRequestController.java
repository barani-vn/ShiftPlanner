package com.shiftplanner.controller;

import com.shiftplanner.entity.SwapRequest;
import com.shiftplanner.service.SwapRequestService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/swaps")
@CrossOrigin
public class SwapRequestController {
    private final SwapRequestService service;
    public SwapRequestController(SwapRequestService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<SwapRequest> create(@RequestBody Map<String, Long> body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            service.create(body.get("requesterId"), body.get("colleagueId"), body.get("shiftId")));
    }
    @GetMapping public List<SwapRequest> all() { return service.all(); }

    @PutMapping("/{id}/colleague/accept")
    public SwapRequest accept(@PathVariable Long id) { return service.colleagueDecision(id, true); }

    @PutMapping("/{id}/colleague/decline")
    public SwapRequest decline(@PathVariable Long id) { return service.colleagueDecision(id, false); }

    @PutMapping("/{id}/manager/approve")
    public SwapRequest approve(@PathVariable Long id) { return service.managerDecision(id, true); }

    @PutMapping("/{id}/manager/reject")
    public SwapRequest reject(@PathVariable Long id) { return service.managerDecision(id, false); }
}
