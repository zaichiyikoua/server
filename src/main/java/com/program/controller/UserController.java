package com.program.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.program.pojo.Token;
import com.program.pojo.User;
import com.program.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/program")
public class UserController {

	@Autowired
	UserServiceImpl service;

	@GetMapping("User/login")
	public Map<String, Object> login(@RequestParam("name") String name, @RequestParam("password") String password,
			HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (name == null && password == null) {
			map.put("code", 400);
			map.put("info", "用户名和密码为空");
			return map;
		}
		// 先查用户，存在用户，再比较密码是否相同
		// 可能存在同名用户
		List<User> user = service.getUserByName(name);
		if (user.isEmpty() == true) {
			map.put("code", 400);
			map.put("info", "用户不存在");
			return map;
		}
		for (User item : user) {
			// 登录成功，加入token
			if (item.getPassword().equals(password)) {
				session.setAttribute("token", Token.getToken());
				map.put("code", 200);
				map.put("info", "登录成功");
				map.put("token", Token.getToken());
				return map;
			}
		}
		// 存在用户但是密码和数据库密码不一致
		map.put("code", 400);
		map.put("info", "密码错误，请重新输入");
		return map;
	}

	@GetMapping("/User/{id}")
	public User getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}

}
