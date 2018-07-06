package com.es.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.es.pojo.Admin;

public interface AdminMapper {

	public Admin checkLogin(@Param(value="username") String username, @Param(value="password") String password);

	public Integer findCount();

	public List<Admin> findAllAdmin();

	public Integer checkName(String username);
	
	public Integer updateAdmin(Admin admin);

	public Integer insertAdmin(Admin admin);
	
	public Admin queryAdmin(String id);
	

}
