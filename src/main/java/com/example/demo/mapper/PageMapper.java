package com.example.demo.mapper;

import com.example.demo.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface PageMapper {

	int addPage(Page page);

	Page selectByID(int id);

	Page selectByTitle(String title);

	// 查询pagename是否存在
	int existTitle(String title);

	int deleteByID(int id);

	ArrayList<Page> allPage();

	ArrayList<Page> search(String keyword);

	void updatePageView(Page page);

	void updatePageStar(Page page);

	void updatePageSourceByUID(@Param("source") String source, @Param("id") int id);
}
