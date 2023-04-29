package com.example.demo.service;

import model.Favorite;

import java.util.ArrayList;

public class FavoriteService {
	int addFavorite(Favorite favorite);

	ArrayList<Favorite> selectByUserID(int id);

	int deleteByID(int id);

	Favorite selectByID(int id);

	boolean existFavorite(Favorite favorite);
	
	int deleteByUserID(int id);

	int deleteBy2ID(Favorite favorite);

	int favoriteNumber(int id);

	int pagefavoriteNumber(int id);

}
