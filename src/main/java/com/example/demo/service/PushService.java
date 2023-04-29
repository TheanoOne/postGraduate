package com.example.demo.service;

import model.Page;
import model.Push;

import java.util.ArrayList;

public class PushService {
	int addPush(Push push);

	ArrayList<Push> search(String keyword);

	Push selectByID(int ID);

	ArrayList<Push> allPush();

	ArrayList<Page> selectByType(String type);

	int deleteByID(int ID);

	boolean existPID(int PID);

	int updateTypeByID(Push push);
}
