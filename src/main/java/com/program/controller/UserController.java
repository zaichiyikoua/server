package com.program.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.program.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/program")
public class UserController {

	@Autowired
	UserServiceImpl service;

	@GetMapping("User/login")
	public Map<String,Object> login(@RequestParam("name") String name, @RequestParam("password") String password) {
		return service.login(name, password);
	}
}
