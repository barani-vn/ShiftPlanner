package com.shiftplanner.service;

import com.shiftplanner.entity.*;
import com.shiftplanner.exception.ResourceNotFoundException;
import com.shiftplanner.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SwapRequestService {
    private final SwapRequestRepository swaps;
    private final EmployeeRepository employees;
    private final ShiftRepository shifts;
    private final RosterRepository rosters;

    public SwapRequestService(SwapRequestRepository swaps, EmployeeRepository employees,
                              ShiftRepository shifts, RosterRepository rosters) {
        this.swaps = swaps; this.employees = employees;
        this.shifts = shifts; this.rosters = rosters;
    }

    public SwapRequest create(Long requesterId, Long colleagueId, Long shiftId) {
        Employee requester = employees.findById(requesterId).orElseThrow(() ->
            new ResourceNotFoundException("Requester not found"));
        Employee colleague = employees.findById(colleagueId).orElseThrow(() ->
            new ResourceNotFoundException("Colleague not found"));
        Shift shift = shifts.findById(shiftId).orElseThrow(() ->
            new ResourceNotFoundException("Shift not found"));

        if (requesterId.equals(colleagueId))
            throw new IllegalArgumentException("Requester and colleague must be different");

        SwapRequest s = new SwapRequest();
        s.setRequester(requester);
        s.setColleague(colleague);
        s.setShift(shift);
        return swaps.save(s);
    }

    public List<SwapRequest> all() { return swaps.findAll(); }

    public SwapRequest get(Long id) {
        return swaps.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Swap request not found"));
    }

    public SwapRequest colleagueDecision(Long id, boolean accept) {
        SwapRequest s = get(id);
        if (s.getColleagueStatus() != SwapStatus.PENDING)
            throw new IllegalArgumentException("Colleague decision already recorded");

        s.setColleagueStatus(accept ? SwapStatus.ACCEPTED : SwapStatus.DECLINED);
        if (!accept) s.setOverallStatus(SwapStatus.DECLINED);
        return swaps.save(s);
    }

    public SwapRequest managerDecision(Long id, boolean approve) {
        SwapRequest s = get(id);
        if (s.getColleagueStatus() != SwapStatus.ACCEPTED)
            throw new IllegalArgumentException("Manager cannot approve until colleague accepts");

        s.setManagerStatus(approve ? SwapStatus.APPROVED : SwapStatus.REJECTED);

        if (!approve) {
            s.setOverallStatus(SwapStatus.REJECTED);
            return swaps.save(s);
        }

        // Final approval: apply the swap by replacing the shift assignment.
        Roster target = rosters.findByEmployeeId(s.getRequester().getId()).stream()
            .filter(r -> r.getShift().getId().equals(s.getShift().getId()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                "Requester is not assigned to the selected shift"));

        target.setEmployee(s.getColleague());
        rosters.save(target);

        s.setOverallStatus(SwapStatus.APPLIED);
        return swaps.save(s);
    }
}
