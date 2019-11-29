package com.program.service;

import java.util.List;
import com.program.pojo.User;

public interface UserService {
	// 通过id获取
	public User getUserById(int userId);

	// 通过loginName获取
	public User getUserByName(String loginName);

	// 分页查询 page为第几页，size为最大显示多少条
	public List<User> getAllUserByPages(int page, int size);

	// 查询所有用户的数量
	public long getAllUserCount();

	// 添加用户
	public int addUser(User user);
}
