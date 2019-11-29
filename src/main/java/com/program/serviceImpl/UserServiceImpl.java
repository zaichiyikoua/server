package com.program.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.program.DAO.UserDAO;
import com.program.pojo.User;
import com.program.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public long getAllUserCount() {
		return userDAO.getAllUserCount();
	}

	@Override
	@Transactional
	public List<User> getAllUserByPages(int page, int size) {
		return userDAO.getAllUserByPages(page, size);
	}

	@Override
	@Transactional
	public User getUserById(int userId) {
		return userDAO.getUserById(userId);
	}

	@Override
	@Transactional
	public User getUserByName(String loginName) {
		return userDAO.getUserByName(loginName);
	}

	@Override
	public int addUser(User user) {
		return userDAO.addUser(user);
	}

}
