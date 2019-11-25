package com.program.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.program.pojo.User;


@Repository
public interface UserDAO {
	//通过id获取
	public User getUserById(int id);
	//通过name获取,可能有重名
	public List<User> getUserByName(String name);
}
