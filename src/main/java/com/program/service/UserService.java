package com.program.service;

import java.util.List;
import com.program.pojo.User;

public interface UserService {

	public User getUserById(int userId);

	public List<User> getUserByName(String loginName);

	public List<User> getAllUserByPages(int page, int size);

	public long getAllUserCount();

}
