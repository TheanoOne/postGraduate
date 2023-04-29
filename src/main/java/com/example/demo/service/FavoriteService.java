package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.Favorite;
import com.example.demo.mapper.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
	FavoriteMapper favoriteMapper;

	public int addFavorite(Favorite favorite) {
		return favoriteMapper.addFavorite(favorite);
	}

	public ArrayList<Favorite> selectByUserID(int id) {
		return favoriteMapper.selectByUserID(id);
	}

	public int deleteByID(int id) {
		return favoriteMapper.deleteByID(id);
	}

	public Favorite selectByID(int id) {
		return favoriteMapper.selectByID(id);
	}

	public boolean existFavorite(Favorite favorite) {
		return favoriteMapper.existFavorite(favorite)>0;
	}

	public int deleteBy2ID(Favorite favorite) {
		return favoriteMapper.deleteBy2ID(favorite);

	}

	public int favoriteNumber(int id) {
		return favoriteMapper.favoriteNumber(id);
	}

	public int pagefavoriteNumber(int id) {
		return favoriteMapper.pagefavoriteNumber(id);
	}

	public int deleteByUserID(int id) {
		return favoriteMapper.deleteByUserID(id);
	}

}
