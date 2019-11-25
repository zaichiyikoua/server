package com.program.service;

import java.util.List;

import com.program.pojo.ResultInfo;
import com.program.pojo.User;

public interface UserService {

	public User getUserById(int id);
	public List<User> getUserByName(String name);
	public ResultInfo login(String name,String password);
}
