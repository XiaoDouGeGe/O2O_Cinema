package com.xjau.cinema.db.dao;

import java.util.ArrayList;

import com.xjau.cinema.domain.Sale;
import com.xjau.cinema.domain.User;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	
	private static final String PATH = "data/data/com.xjau.cinema/files/cinema.db";// 注意,该路径必须是data/data目录的文件,否则数据库访问不到
	private static Cursor cursor = null;

	//在登录的时候，根据account和password判断是否登录成功
	public static boolean checkAccount(String textAccount, String textPassword){
		String password = null;
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
		cursor = database.rawQuery("select password from User where account = ?", new String[] {textAccount});
		//获取数据库中真实的密码password
		if(cursor.moveToNext()) {
			password = cursor.getString(0);
		} 
		
		if (textPassword.equals(password)) {
			return true;
		}else {
			return false;
		}	
	}
	
	
	//在注册是时候，根据account判断此account是否已被注册
	public static boolean isRegistered(String textAccount) {
		String account = null;
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
		cursor = database.rawQuery("select account from User where account = ?", new String[] {textAccount});
		if(cursor.moveToNext()) {
			account = cursor.getString(0);
		}
		
		if (account == null) {
			return true;
		}else {
			return false;
		}	
	}
	

	
	//注册成功了，往User表中插入数据
	public static void insertUser(String textAccount, String textPassword){
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
		database.execSQL("insert into User(account, password) values(?,?)", new String[]{textAccount, textPassword});
	}
	
	//更新User表，即更新密码
	public static void updateUser(String textAccount, String newPassword) {
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
		database.execSQL("update User set password = ? where account = ?", new String[]{newPassword, textAccount});
	}
	
	//查找User表
	public static ArrayList<User> getAllUserList() {
		ArrayList<User> userList = new ArrayList<User>();
		
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
			
			cursor = database.rawQuery("select account,password from User", null);			
			while(cursor.moveToNext()) {
				String userAccount = cursor.getString(0);
				String userPassword = cursor.getString(1);
				User user = new User(userAccount,userPassword);
				userList.add(user);
			}
	
		return userList;
	}
	
	//删除User表
	public static void deleteUser(String textAccount, String textPassword){
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
		database.delete("User", "account=? and password = ?", new String[]{textAccount, textPassword});
		}
	
	
}
