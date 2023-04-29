package com.example.demo.service;

import model.Reviews;

import java.util.ArrayList;

public class ReviewsService {
	int addReviews(Reviews reviews);

	ArrayList<Reviews> selectByPage(int id);

    int deleteByID(int id);
    
    Reviews selectByID(int ID);
    
    ArrayList<Reviews> selectByUserID(int UID);
    
    int reviewsNumber(int UID);
    
    int deleteByUserID(int ID);
    }
