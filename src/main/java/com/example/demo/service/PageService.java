package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.Page;
import com.example.demo.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {
	@Autowired
	PageMapper pageMapper;

	public int addPage(Page page) {
		Boolean isexist= existTitle(page.getTitle());
		if(isexist){
			return 1;
		}else{
			pageMapper.addPage(page);
			return 0;
		}
	}

	public Page selectByID(int id) {
		return pageMapper.selectByID(id);
	}

	public Page selectByTitle(String title) {
		return pageMapper.selectByTitle(title);
	}

	public Boolean existTitle(String title) {
		return pageMapper.existTitle(title)>0;
	}

	public int deleteByID(int id) {
		return pageMapper.deleteByID(id);
	}

	public ArrayList<Page> allPage() {
		return pageMapper.allPage();
	}

	public ArrayList<Page> search(String keyword) {
		return pageMapper.search(keyword);
	}

	public void updatePageView(Page page) {
		pageMapper.updatePageView(page);
	}

	public void updatePageStar(Page page) {
		pageMapper.updatePageStar(page);
	}

	public void updatePageSourceByUID(String source, int id) {
		pageMapper.updatePageSourceByUID(source, id);
	}
}
