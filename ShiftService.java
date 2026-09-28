package com.shiftplanner.service;

import com.shiftplanner.entity.Shift;
import com.shiftplanner.exception.ResourceNotFoundException;
import com.shiftplanner.repository.ShiftRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShiftService {
    private final ShiftRepository repository;
    public ShiftService(ShiftRepository repository) { this.repository = repository; }

    public Shift create(Shift s) {
        if (!s.getEndTime().isAfter(s.getStartTime()))
            throw new IllegalArgumentException("End time must be after start time");
        return repository.save(s);
    }
    public List<Shift> all() { return repository.findAll(); }
    public Shift get(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Shift not found: " + id));
    }
    public void delete(Long id) { repository.delete(get(id)); }
}
