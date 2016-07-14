package com.xjau.cinema.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AdminDao {

	private static final String PATH = "data/data/com.xjau.cinema/files/cinema.db";// 注意,该路径必须是data/data目录的文件,否则数据库访问不到
	private static Cursor cursor = null;
	
	//在登录的时候，根据account和password判断是否登录成功
	public static boolean checkAccount(String textAccount, String textPassword){
		String ad_password = null;
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
		cursor = database.rawQuery("select ad_password from Admin where ad_account = ?", new String[] {textAccount});
		//获取数据库中真实的密码password
		if(cursor.moveToNext()) {
			ad_password = cursor.getString(0);
		} 
		
		if (textPassword.equals(ad_password)) {
			return true;
		}else {
			return false;
		}	
	}
		
}
