package com.shiftplanner.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "swap_request")
public class SwapRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Employee requester;

    @ManyToOne(optional = false)
    private Employee colleague;

    @ManyToOne(optional = false)
    private Shift shift;

    @Enumerated(EnumType.STRING)
    private SwapStatus colleagueStatus = SwapStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private SwapStatus managerStatus = SwapStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private SwapStatus overallStatus = SwapStatus.PENDING;

    private LocalDateTime requestedAt = LocalDateTime.now();

    public SwapRequest() {}

    public Long getId() { return id; }
    public Employee getRequester() { return requester; }
    public void setRequester(Employee requester) { this.requester = requester; }
    public Employee getColleague() { return colleague; }
    public void setColleague(Employee colleague) { this.colleague = colleague; }
    public Shift getShift() { return shift; }
    public void setShift(Shift shift) { this.shift = shift; }
    public SwapStatus getColleagueStatus() { return colleagueStatus; }
    public void setColleagueStatus(SwapStatus colleagueStatus) { this.colleagueStatus = colleagueStatus; }
    public SwapStatus getManagerStatus() { return managerStatus; }
    public void setManagerStatus(SwapStatus managerStatus) { this.managerStatus = managerStatus; }
    public SwapStatus getOverallStatus() { return overallStatus; }
    public void setOverallStatus(SwapStatus overallStatus) { this.overallStatus = overallStatus; }
    public LocalDateTime getRequestedAt() { return requestedAt; }
}
