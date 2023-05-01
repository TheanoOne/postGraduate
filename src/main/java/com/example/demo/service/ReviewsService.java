package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.Reviews;
import com.example.demo.mapper.ReviewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsService {
    @Autowired
    ReviewsMapper reviewsMapper;

    public int addReviews(Reviews reviews) {
        return reviewsMapper.addReviews(reviews);
    }

    public ArrayList<Reviews> selectByPage(int id) {
        return reviewsMapper.selectByPage(id);
    }

    public int deleteByID(int id) {
        return reviewsMapper.deleteByID(id);
    }

    public Reviews selectByID(int ID) {
        return reviewsMapper.selectByID(ID);
    }

        public ArrayList<Reviews> selectByUserID(int UID) {
        return reviewsMapper.selectByUserID(UID);
    }

        public int reviewsNumber(int UID) {
        return reviewsMapper.reviewsNumber(UID);
    }

    public int deleteByUserID(int ID) {
        return reviewsMapper.deleteByUserID(ID);
    }

}
