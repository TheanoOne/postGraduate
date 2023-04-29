package com.example.demo.service;

import model.Page;

import java.util.ArrayList;

public class PageServce {

	int addPage(Page page);

	Page selectByID(int id);

	Page selectByTitle(String title);

	// 查询pagename是否存在
	Boolean existTitle(String title);

	int deleteByID(int id);

	ArrayList<Page> allPage();

	ArrayList<Page> search(String keyword);

	void updatePageView(Page page);

	void updatePageStar(Page page);

	void updatePageSourceByUID(String source, int id);
}
