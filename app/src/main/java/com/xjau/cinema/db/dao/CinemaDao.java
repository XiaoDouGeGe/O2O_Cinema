package com.xjau.cinema.db.dao;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xjau.cinema.domain.Cinema;

public class CinemaDao {
	
	private static final String PATH = "data/data/com.xjau.cinema/files/cinema.db";// 注意,该路径必须是data/data目录的文件,否则数据库访问不到
	private static Cursor cursor = null;

	public static ArrayList<Cinema> getCinemaList(String area) {
		
		
		
		ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
		
		//ArrayList<Integer> IDs = new ArrayList<Integer>();
		
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
		

		Log.d("sqlitewxp", "area = "+ area);

		

//			cursor = null;
			cursor = database.rawQuery("select cinema_name,price from Cinema where area_name = ?", new String[] {area});			
			while(cursor.moveToNext()) {
				String cinema_name = cursor.getString(0);
				String areaName = area;
				double ticketPrice = cursor.getDouble(1);
				Cinema cinema = new Cinema(cinema_name,areaName,ticketPrice);
				cinemaList.add(cinema);
			}
			
//			for(Cinema ci:cinemaList){
//				Log.v("cinema", ci.toString());
//			}
	
		
		return cinemaList;
	}
}
