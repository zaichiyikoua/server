package com.program.service;

import java.util.List;
import com.program.pojo.User;

public interface UserService {

	public User getUserById(int id);

	public List<User> getUserByName(String name);

	public List<User> getAllUserByPages(int page, int size);

	public long getAllUserCount();

}
