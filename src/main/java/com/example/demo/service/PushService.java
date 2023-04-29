package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.Page;
import com.example.demo.entity.Push;
import com.example.demo.mapper.PushMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushService {
	PushMapper pushMapper;
	/* (non-Javadoc)
	 * @see service.PushService#addPush(model.Push)
	 */

	public int addPush(Push push) {
		// TODO Auto-generated method stub
		return pushMapper.addPush(push);
	}

	/* (non-Javadoc)
	 * @see service.PushService#search(java.lang.String)
	 */

	public ArrayList<Push> search(String keyword) {
		// TODO Auto-generated method stub
		return pushMapper.search(keyword);
	}

	/* (non-Javadoc)
	 * @see service.PushService#selectByID(int)
	 */

	public Push selectByID(int ID) {
		// TODO Auto-generated method stub
		return pushMapper.selectByID(ID);
	}

	/* (non-Javadoc)
	 * @see service.PushService#selectByType(java.lang.String)
	 */

	public ArrayList<Page> selectByType(String type) {
		ArrayList<Push> pushs = pushMapper.selectByType(type);
		ArrayList<Page> pages = new ArrayList<Page>();
		for (Push push : pushs) {
			pages.add(push.getPage());
		}

		return pages;
	}

	/* (non-Javadoc)
	 * @see service.PushService#deleteByID(int)
	 */

	public int deleteByID(int ID) {
		// TODO Auto-generated method stub
		return pushMapper.deleteByID(ID);
	}

	public ArrayList<Push> allPush() {
		// TODO Auto-generated method stub
		return pushMapper.allPush();
	}


	public boolean existPID(int PID) {
		// TODO Auto-generated method stub
		return pushMapper.existPID(PID)>0;
	}


	public int updateTypeByID(Push push) {
		// TODO Auto-generated method stub
		return pushMapper.updateTypeByID(push);
	}

}
