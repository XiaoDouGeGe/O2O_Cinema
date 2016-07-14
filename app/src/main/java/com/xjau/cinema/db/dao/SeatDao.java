package com.xjau.cinema.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SeatDao {

	private static final String PATH = "data/data/com.xjau.cinema/files/cinema.db";// 注意,该路径必须是data/data目录的文件,否则数据库访问不到
	private static Cursor cursor = null;
	
	private static String TABLE;
	public static String SEAT_ID;
	private static int STATUS;
	/*
	 * 查找座位的当前状态
	 */
	public static int getSeatStatus(String filmSeat, int ID){
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
		
		//根据电影名称决定查找哪一个表格，左耳-SeatOne 何以笙箫默—SeatTwo...
		if(filmSeat.equals("左耳")){
			TABLE = "SeatOne";
		}
		else if(filmSeat.equals("何以笙箫默")){
			TABLE = "SeatTwo";
		}
		else if(filmSeat.equals("神话")){
			TABLE = "SeatThree";
		}
		else if(filmSeat.equals("分手大师")){
			TABLE = "SeatFour";
		}
		else if(filmSeat.equals("喜剧之王")){
			TABLE = "SeatFive";
		}
		else if(filmSeat.equals("匆匆那年")){
			TABLE = "SeatSix";
		}
		
		cursor = database.rawQuery("select status from "+ TABLE + " where seat_id = ?", new String[] {"ID"});			
		if(cursor.moveToNext()) {
			STATUS = cursor.getInt(0);
		}
		return STATUS;
	}
	
	
	/*
	 * 将座位的status由0设置为1 
	 */
	public static void setSeatStatus(String film, int seatNum){
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
		
		//根据电影名称决定设置哪一个表格，左耳-SeatOne 何以笙箫默—SeatTwo...
		if(film.equals("左耳")){
			TABLE = "SeatOne";
		}
		else if(film.equals("何以笙箫默")){
			TABLE = "SeatTwo"; 
		}
		else if(film.equals("神话")){
			TABLE = "SeatThree";
		}
		else if(film.equals("分手大师")){
			TABLE = "SeatFour";
		}
		else if(film.equals("喜剧之王")){
			TABLE = "SeatFive";
		}
		else if(film.equals("匆匆那年")){
			TABLE = "SeatSix";
		}
		
		//修改SQL语句  
		ContentValues cv = new ContentValues();
        cv.put("status", 1);
        String[] args = {String.valueOf(seatNum)};
		database.update(TABLE,cv,"seat_id = ?",args);
		
	}
	
}
