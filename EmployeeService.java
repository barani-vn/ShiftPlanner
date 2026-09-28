package com.shiftplanner.service;

import com.shiftplanner.entity.Employee;
import com.shiftplanner.exception.ResourceNotFoundException;
import com.shiftplanner.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) { this.repository = repository; }

    public Employee create(Employee e) { return repository.save(e); }
    public List<Employee> all() { return repository.findAll(); }
    public Employee get(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Employee not found: " + id));
    }
    public Employee update(Long id, Employee e) {
        Employee old = get(id);
        old.setName(e.getName());
        old.setEmail(e.getEmail());
        old.setRole(e.getRole());
        return repository.save(old);
    }
    public void delete(Long id) { repository.delete(get(id)); }
}
