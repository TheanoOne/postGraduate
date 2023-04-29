package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
	
	int addUser(User user);

    User selectByID(int id);

    User selectByAccount(String account);

    boolean updateByID(User user);

    //查询username是否存在
    int existAccount(String account);

    //查询用户数
    int getUserCount();
    
    int deleteByID(int id);
    
    int closeUserByID(int id);
    
    int openUserByID(int id);

	ArrayList<User> allUser();
	
	int updataUser(User user);
	
	ArrayList<User> search(String keyword);
}
