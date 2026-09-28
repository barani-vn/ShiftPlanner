package com.shiftplanner.controller;

import com.shiftplanner.entity.Employee;
import com.shiftplanner.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee e) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(e));
    }
    @GetMapping public List<Employee> all() { return service.all(); }
    @GetMapping("/{id}") public Employee get(@PathVariable Long id) { return service.get(id); }
    @PutMapping("/{id}") public Employee update(@PathVariable Long id, @Valid @RequestBody Employee e) {
        return service.update(id, e);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id); return ResponseEntity.noContent().build();
    }
}
