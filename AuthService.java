package com.shiftplanner.service;

import com.shiftplanner.entity.Employee;
import com.shiftplanner.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AuthService {
    private final EmployeeRepository repo;
    public AuthService(EmployeeRepository repo){this.repo=repo;}
    public Map<String,Object> login(String username,String password){
        Employee e=repo.findAll().stream().filter(x -> username.equals(x.getUsername()) && password.equals(x.getPassword())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        Map<String,Object> out=new LinkedHashMap<>();
        out.put("id",e.getId()); out.put("name",e.getName()); out.put("username",e.getUsername()); out.put("role",e.getRole());
        return out;
    }
}
