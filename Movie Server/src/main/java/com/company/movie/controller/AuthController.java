package com.company.movie.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.movie.entity.User;
import com.company.movie.entity.dto.UserDTO;
import com.company.movie.service.AuthService;

@RestController
@RequestMapping(value = "auth")
@CrossOrigin(origins = {"http://movieapplication.s3-website.us-east-2.amazonaws.com","http://ec2-18-219-121-39.us-east-2.compute.amazonaws.com:4200", "http://localhost:4200", "http://localhost:3000"})
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody User user, HttpServletResponse response) {
		return ResponseEntity.ok().body(authService.login(user, response));
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@RequestBody User user) {
		return ResponseEntity.ok().body(authService.register(user));
	}

	@GetMapping("/user")
	public ResponseEntity<List<User>> getusers() {
		return ResponseEntity.ok().body(authService.getusers());
	}

}
