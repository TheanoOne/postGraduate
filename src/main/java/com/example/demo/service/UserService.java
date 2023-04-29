package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	public UserMapper userDao;

	public int addUser(User user) {
		Boolean isexist= existAccount(user.getAccount());
		if(isexist){
			return 1;
		}else{
			userDao.addUser(user);
			return 2;
		}
	}

	public int login(String account, String password) {
		// 判断username是否存在
		boolean existAccount = existAccount(account);
		// 若username存在，验证密码
		if (existAccount) {
			User resUser = userDao.selectByAccount(account);
			if(resUser.getStatus()!=0){
				return 3;
			}
			if (resUser.getPassword().equals(password)) {
				return 2;
			}
			return 1;
		}
		return 0;
	}

	public boolean existAccount(String account) {
		return userDao.existAccount(account) > 0;
	}

	public User getUserById(Integer id) {
		return userDao.selectByID(id);
	}

	public int getUserCount() {
		return userDao.getUserCount();
	}

	public boolean updateUserByID(User user) {
		return userDao.updateByID(user);
	}

	public int deleteByID(int ID) {
		return userDao.deleteByID(ID);
	}

	public User getUserByAccount(String account) {
		return userDao.selectByAccount(account);
	}

	public ArrayList<User> allUser() {

		return userDao.allUser();
	}

	public ArrayList<User> search(String keyword) {
		return userDao.search(keyword);
	}

}
