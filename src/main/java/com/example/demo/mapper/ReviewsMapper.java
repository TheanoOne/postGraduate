package com.example.demo.mapper;

import com.example.demo.entity.Reviews;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ReviewsMapper {
	
	int addReviews(Reviews reviews);

	ArrayList<Reviews> selectByPage(int id);

    int deleteByID(int ID);
    
    Reviews selectByID(int ID);
    
    ArrayList<Reviews> selectByUserID(int UID);
    
    int reviewsNumber(int UID);
    
    int deleteByUserID(int ID);
}
