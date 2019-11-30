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
public class UserController {

	@Autowired
	UserServiceImpl service;

	// 登录接口
	@GetMapping("/login")
	public Map<String, Object> login(@RequestParam("loginName") String loginName,
			@RequestParam("password") String password, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (loginName == null || password == null || loginName.equals("") || password.equals("")) {
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
			// 新增 添加权限信息
			session.setAttribute("authority", user.getAuthority());
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

	// 添加用户接口
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

	// 通过Id查询接口
	@GetMapping("/{userId}")
	public Map<String, Object> getUserById(@PathVariable("userId") int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
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

	// 修改用户信息接口
	// 一般情况为修改密码和权限
	@PostMapping("/updateUser")
	public Map<String, Object> updateUser(User user, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			map.put("info", "请输入您要修改的参数");
			map.put("code", 400);
			return map;
		}
		int authority = (int) session.getAttribute("authority");
		// 只有超级管理员才能修改用户
		// 设定不能修改登录账号（loginName）
		if (authority != 1) {
			map.put("info", "您没有权限");
			map.put("code", 400);
			return map;
		}
		int updataUser = service.updataUser(user);
		if (updataUser > 0) {
			map.put("info", "修改成功");
			map.put("code", 200);
			return map;
		}
		map.put("info", "修改失败");
		map.put("code", 400);
		return map;
	}

	// 通过loginName删除用户接口
	@RequestMapping("/deleteUser")
	public Map<String, Object> deleteUser(String loginName, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (loginName == null || loginName.equals("")) {
			map.put("info", "请输入要删除的用户登录名称");
			map.put("code", 400);
			return map;
		}
		int authority = (int) session.getAttribute("authority");
		// 只有超级管理员才能删除用户
		if (authority != 1) {
			map.put("info", "您没有权限");
			map.put("code", 400);
			return map;
		}
		int deleteUser = service.deleteUser(loginName);
		if (deleteUser > 0) {
			map.put("info", "删除成功");
			map.put("code", 200);
			return map;
		}
		map.put("info", "删除失败");
		map.put("code", 400);
		return map;

	}
}
