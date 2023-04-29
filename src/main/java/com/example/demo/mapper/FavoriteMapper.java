package com.example.demo.mapper;

import com.example.demo.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface FavoriteMapper {

	int addFavorite(Favorite favorite);

	ArrayList<Favorite> selectByUserID(int id);

	int deleteByID(int id);
	
	int deleteByUserID(int id);
	
	Favorite selectByID(int id);

	int existFavorite(int PID, int UID);

	int deleteBy2ID(Favorite favorite);

	int existFavorite(Favorite favorite);

	int favoriteNumber(int id);

	int pagefavoriteNumber(int id);

}
