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
	@Transactional
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	@Transactional
	public List<User> getUserByName(String name) {
		return userDAO.getUserByName(name);
	}



}
