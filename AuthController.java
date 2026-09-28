package com.shiftplanner.controller;
import com.shiftplanner.service.AuthService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service){this.service=service;}
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,String> body){return service.login(body.get("username"),body.get("password"));}
}
