package com.program.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	@Transactional
	public Map<String,Object> login(String name, String password) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		if (name == null && password == null) {
			map.put("code", 400);
			map.put("info", "用户名和密码为空");
			return map;
		}	
		//可能存在同名用户
		List<User> user = userDAO.getUserByName(name);		
		if (user.isEmpty() == true) {
			map.put("code", 400);
			map.put("info", "用户不存在");
			return map;
		}
		for (User item : user) {
			//简单判断，后面再改
			if (item.getPassword().equals(password)) {
				map.put("code", 200);
				map.put("indo", "登录成功");
				return map;
			}
		}
		//存在用户但是密码和数据库密码不一致
		map.put("code", 400);
		map.put("info", "密码错误，请重新输入");
		return map;
		
	}

}
