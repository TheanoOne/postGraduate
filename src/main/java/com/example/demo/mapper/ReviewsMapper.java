package com.example.demo.mapper;

import model.Reviews;

import java.util.ArrayList;

public interface ReviewsMapper {
	
	int addReviews(Reviews reviews);

	ArrayList<Reviews> selectByPage(int id);

    int deleteByID(int ID);
    
    Reviews selectByID(int ID);
    
    ArrayList<Reviews> selectByUserID(int UID);
    
    int reviewsNumber(int UID);
    
    int deleteByUserID(int ID);
}
