package com.program.service;

import java.util.List;
import java.util.Map;

import com.program.pojo.User;

public interface UserService {

	public User getUserById(int id);
	public List<User> getUserByName(String name);
	public Map<String,Object> login(String name,String password);
}
