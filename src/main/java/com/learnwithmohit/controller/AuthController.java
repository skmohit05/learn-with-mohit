package com.learnwithmohit.controller;

import com.learnwithmohit.entity.JwtRequest;
import com.learnwithmohit.entity.JwtResponse;
import com.learnwithmohit.entity.User;
import com.learnwithmohit.service.JwtService;
import com.learnwithmohit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/public")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

    @PostMapping({"/registerUser"})
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
}
