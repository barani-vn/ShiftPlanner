package com.shiftplanner.controller;

import com.shiftplanner.entity.Roster;
import com.shiftplanner.service.RosterService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rosters")
@CrossOrigin
public class RosterController {
    private final RosterService service;
    public RosterController(RosterService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Roster> assign(@RequestBody Map<String, Long> body) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.assign(body.get("employeeId"), body.get("shiftId")));
    }
    @GetMapping public List<Roster> all() { return service.all(); }
    @GetMapping("/{id}") public Roster get(@PathVariable Long id) { return service.get(id); }
}
