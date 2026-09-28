package com.shiftplanner.service;

import com.shiftplanner.entity.*;
import com.shiftplanner.exception.ResourceNotFoundException;
import com.shiftplanner.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class RosterService {
    private final RosterRepository rosterRepository;
    private final EmployeeRepository employeeRepository;
    private final ShiftRepository shiftRepository;

    public RosterService(RosterRepository r, EmployeeRepository e, ShiftRepository s) {
        rosterRepository = r; employeeRepository = e; shiftRepository = s;
    }

    public Roster assign(Long employeeId, Long shiftId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
            new ResourceNotFoundException("Employee not found"));
        Shift shift = shiftRepository.findById(shiftId).orElseThrow(() ->
            new ResourceNotFoundException("Shift not found"));

        for (Roster existing : rosterRepository.findByEmployeeId(employeeId)) {
            Shift other = existing.getShift();
            if (other.getShiftDate().equals(shift.getShiftDate()) &&
                shift.getStartTime().isBefore(other.getEndTime()) &&
                other.getStartTime().isBefore(shift.getEndTime())) {
                throw new IllegalArgumentException(
                    "Employee already has an overlapping shift on " + shift.getShiftDate());
            }
        }

        Roster roster = new Roster();
        roster.setEmployee(employee);
        roster.setShift(shift);
        return rosterRepository.save(roster);
    }

    public List<Roster> all() { return rosterRepository.findAll(); }
    public Roster get(Long id) {
        return rosterRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Roster entry not found"));
    }
}
