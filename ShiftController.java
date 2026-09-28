package com.shiftplanner.controller;

import com.shiftplanner.entity.Shift;
import com.shiftplanner.service.ShiftService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shifts")
@CrossOrigin
public class ShiftController {
    private final ShiftService service;
    public ShiftController(ShiftService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Shift> create(@Valid @RequestBody Shift s) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(s));
    }
    @GetMapping public List<Shift> all() { return service.all(); }
    @GetMapping("/{id}") public Shift get(@PathVariable Long id) { return service.get(id); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id); return ResponseEntity.noContent().build();
    }
}
