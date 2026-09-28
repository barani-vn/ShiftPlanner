package com.shiftplanner.repository;
import com.shiftplanner.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
public interface RosterRepository extends JpaRepository<Roster, Long> {
    List<Roster> findByEmployeeId(Long employeeId);
}
