package com.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.pojo.ResultInfo;
import com.program.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/program")
public class UserController {

	@Autowired
	UserServiceImpl service;

	@GetMapping("User/login")
	public ResultInfo login(String name, String password) {
		return service.login(name, password);
	}
}
