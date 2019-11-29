package com.program.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.program.pojo.User;

@Repository
public interface UserDAO {
	// 通过id获取
	public User getUserById(@Param("userId") int userId);

	// 通过loginName获取,可能有重名
	public List<User> getUserByName(@Param("loginName") String loginName);

	// 通过用户名获取密码
	public String getPasswordByName(@Param("loginName") String loginName);

	// 查询所有用户的数量
	public long getAllUserCount();

	// 分页查询 page为第几页，size为最大显示多少条
	public List<User> getAllUserByPages(@Param("page") int page, @Param("size") int size);
}
