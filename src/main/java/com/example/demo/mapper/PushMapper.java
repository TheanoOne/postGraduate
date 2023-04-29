package com.example.demo.mapper;

import com.example.demo.entity.Push;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PushMapper {
	int addPush(Push push);

	ArrayList<Push> allPush();

	ArrayList<Push> search(String keyword);

	Push selectByID(int ID);

	ArrayList<Push> selectByType(String type);

	int deleteByID(int ID);

	int existPID(int PID);

	int updateTypeByID(Push push);
}
