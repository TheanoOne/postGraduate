package com.example.demo.mapper;

import com.example.demo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface AdminMapper {
	int addAdmin(Admin admin);

	Admin selectByAccount(String account);

	Admin selectByID(int ID);

	Integer isExistAccount(String account);

	int deleteByID(int id);

	ArrayList<Admin> allAdmin();
	
	int updataAdmin(Admin admin);
	
	ArrayList<Admin> search(String keyword);
}
