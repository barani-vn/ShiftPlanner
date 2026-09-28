package com.shiftplanner.repository;
import com.shiftplanner.entity.SwapRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SwapRequestRepository extends JpaRepository<SwapRequest, Long> {
    List<SwapRequest> findByRequesterId(Long employeeId);
    List<SwapRequest> findByColleagueId(Long employeeId);
}
