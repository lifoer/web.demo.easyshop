package com.es.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.es.pojo.User;

public interface UserMapper {

	public User checkLogin(@Param("username") String username, @Param("password") String password);

	public Integer checkRegister(String username);

	public Integer insertUser(User user);
	
	public List<User> findAllUser();

	public Integer findCount();

	public Integer updateUser(User user);

	public User queryUser(String id);
	

}
