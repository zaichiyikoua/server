package com.program.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.program.DAO.UserDAO;
import com.program.pojo.ResultInfo;
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

	@Override
	@Transactional
	public ResultInfo login(String name, String password) {
		if (name == null && password == null) {
			return new ResultInfo(400, "用户名密码为空");
		}
		//可能存在同名用户
		List<User> user = userDAO.getUserByName(name);		
		if (user.isEmpty() == true) {
			return new ResultInfo(400, "用户不存在");
		}
		for (User item : user) {
			//简单判断，后面再改
			if (item.getPassword().equals(password)) {
				return new ResultInfo(200, "登录成功");
			}
		}
		//存在用户但是密码和数据库密码不一致
		return new ResultInfo(400, "密码错误，请重新输入");
		
	}

}
