package com.springboot.exam.jwtsecurity.controller;

import com.springboot.exam.jwtsecurity.common.DataSettings;
import com.springboot.exam.jwtsecurity.model.Users;
import com.springboot.exam.jwtsecurity.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = DataSettings.CROSS_ORIGIN)
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    public ResponseEntity<String> SayHello(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> userList = homeService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

}
