package com.shiftplanner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roster")
public class Roster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Employee employee;

    @ManyToOne(optional = false)
    private Shift shift;

    public Roster() {}

    public Long getId() { return id; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public Shift getShift() { return shift; }
    public void setShift(Shift shift) { this.shift = shift; }
}
