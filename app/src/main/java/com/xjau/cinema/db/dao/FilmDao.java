package com.xjau.cinema.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FilmDao {

	private static final String PATH = "data/data/com.xjau.cinema/files/cinema.db";// 注意,该路径必须是data/data目录的文件,否则数据库访问不到
	private static Cursor cursor = null;
	static String filmName;
	
	public static int getFirstFilmID(String selectedCinemaName) {
		int firstFilmID = 0;	
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);		
		cursor = database.rawQuery("select show1 from Show where cinema_id = (select cinema_id from Cinema where cinema_name = ?)", new String[] {selectedCinemaName});			
		if(cursor.moveToNext()) {
			firstFilmID = cursor.getInt(0);
		}
		return firstFilmID;		
	}
	
	public static int getSecondFilmID(String selectedCinemaName) {
		int firstFilmID = 0;	
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);		
		cursor = database.rawQuery("select show2 from Show where cinema_id = (select cinema_id from Cinema where cinema_name = ?)", new String[] {selectedCinemaName});			
		if(cursor.moveToNext()) {
			firstFilmID = cursor.getInt(0);
		}
		return firstFilmID;		
	}
	
	public static int getThirdFilmID(String selectedCinemaName) {
		int firstFilmID = 0;	
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);		
		cursor = database.rawQuery("select show3 from Show where cinema_id = (select cinema_id from Cinema where cinema_name = ?)", new String[] {selectedCinemaName});			
		if(cursor.moveToNext()) {
			firstFilmID = cursor.getInt(0);
		}
		return firstFilmID;		
	}
	
	public static int getFourFilmID(String selectedCinemaName) {
		int firstFilmID = 0;	
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);		
		cursor = database.rawQuery("select show4 from Show where cinema_id = (select cinema_id from Cinema where cinema_name = ?)", new String[] {selectedCinemaName});			
		if(cursor.moveToNext()) {
			firstFilmID = cursor.getInt(0);
		}
		return firstFilmID;		
	}
	
	public static int getFiveFilmID(String selectedCinemaName) {
		int firstFilmID = 0;	
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);		
		cursor = database.rawQuery("select show5 from Show where cinema_id = (select cinema_id from Cinema where cinema_name = ?)", new String[] {selectedCinemaName});			
		if(cursor.moveToNext()) {
			firstFilmID = cursor.getInt(0);
		}
		return firstFilmID;		
	}
	
	public static int getSixFilmID(String selectedCinemaName) {
		int firstFilmID = 0;	
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);		
		cursor = database.rawQuery("select show6 from Show where cinema_id = (select cinema_id from Cinema where cinema_name = ?)", new String[] {selectedCinemaName});			
		if(cursor.moveToNext()) {
			firstFilmID = cursor.getInt(0);
		}
		return firstFilmID;		
	}
	
	
	public static String getFilmName(int filmID){
		
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
		cursor = database.rawQuery("select film_name from Film where film_id = ?", new String[] {String.valueOf(filmID)});			
		if(cursor.moveToNext()) {
			filmName = cursor.getString(0);
		}
		
		return filmName;
	}

	
}
