package com.program.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.program.pojo.Token;
import com.program.pojo.User;
import com.program.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/program/User")
public class UserController<V> {

	@Autowired
	UserServiceImpl service;

	// 登录接口
	@GetMapping("/login")
	public Map<String, Object> login(@RequestParam("loginName") String loginName,
			@RequestParam("password") String password, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (loginName == null || password == null) {
			map.put("code", 400);
			map.put("info", "请输入用户名和密码");
			return map;
		}
		// 先查用户，存在用户，再比较密码是否相同
		User user = service.getUserByName(loginName);
		if (user == null) {
			map.put("code", 400);
			map.put("info", "用户不存在");
			return map;
		}
		// 登录成功，加入token
		if (user.getPassword().equals(password)) {
			session.setAttribute("token", Token.getToken());
			map.put("code", 200);
			map.put("info", "登录成功");
			map.put("token", Token.getToken());
			return map;
		}
		// 存在用户但是密码和数据库密码不一致
		map.put("code", 400);
		map.put("info", "密码错误，请重新输入");
		return map;
	}

	// 退出登录接口
	@RequestMapping("/logout")
	public void logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
	}

	// 分页查询接口
	@GetMapping("/getAllUserByPage")
	public Map<String, Object> getAllUserByPage(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int size) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page <= 0 || size <= 0) {
			map.put("info", "请输入有效的条件");
			map.put("code", 400);
			return map;
		}
		// 进行计算
		int offset = (page - 1) * size;
		long allUserCount = service.getAllUserCount();
		List<User> allUserByPages = service.getAllUserByPages(offset, size);

		map.put("count", allUserCount);
		map.put("data", allUserByPages);
		map.put("code", 200);

		return map;
	}

	// 添加用户
	@PostMapping("/addUser")
	public Map<String, Object> addUser(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			map.put("info", "请输入参数");
			map.put("code", 400);
			return map;
		}
		// 先检查用户是否已存在，不存在则添加
		User check = service.getUserByName(user.getLoginName());
		if (check != null) {
			map.put("info", "该用户已存在，请重新输入");
			map.put("code", 400);
			return map;
		}
		int result = service.addUser(user);
		if (result > 0) {
			map.put("info", "添加成功");
			map.put("code", 200);
			return map;
		}
		map.put("info", "添加失败");
		map.put("code", 400);
		return map;
	}

	// 通过Id查询
	@GetMapping("/{userId}")
	public Map<String, Object> getUserById(@PathVariable("userId") int userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		if (userId <= 0) {
			map.put("info", "请输入有效的查询条件");
			map.put("code", 400);
			return map;
		}		
		User userById = service.getUserById(userId);
		map.put("code", 200);
		map.put("data", userById);
		return map;
	}

}
