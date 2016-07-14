package com.xjau.cinema.db.dao;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xjau.cinema.domain.Sale;

public class SalesDao {

	private static final String PATH = "data/data/com.xjau.cinema/files/cinema.db";// 注意,该路径必须是data/data目录的文件,否则数据库访问不到
	private static Cursor cursor = null;
	
	
	public static void insertSales(String pay_account,String pay_cinema,String pay_film,String pay_showTime,String pay_seats){
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
		
		//往Sales表中插入数据
		database.execSQL("insert into Sales(payAccount, payCinema, payFilm, payShowTime, seats) values(?,?,?,?,?)",
				new String[]{pay_account,pay_cinema,pay_film,pay_showTime,pay_seats});	
	}
	
	
	//在退票的页面，先查找出SaleList
	public static ArrayList<Sale> getSaleList(String account) {
		ArrayList<Sale> saleList = new ArrayList<Sale>();
		
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
		
			cursor = database.rawQuery("select payAccount,payCinema,payFilm,payShowTime,seats from Sales where payAccount = ?", new String[] {account});			
			while(cursor.moveToNext()) {
				String pay_account = cursor.getString(0);
				String pay_cinema = cursor.getString(1);
				String pay_film = cursor.getString(2);
				String pay_showTime = cursor.getString(3);
				String pay_seats = cursor.getString(4);
				Sale sale = new Sale(pay_account, pay_cinema, pay_film, pay_showTime, pay_seats);
				saleList.add(sale);
			}
	
		return saleList;
	}
	//查询所有
	public static ArrayList<Sale> getAllSaleList() {
		ArrayList<Sale> saleList = new ArrayList<Sale>();
		
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
		
		cursor = database.rawQuery("select payAccount,payCinema,payFilm,payShowTime,seats from Sales", null);			
		while(cursor.moveToNext()) {
			String pay_account = cursor.getString(0);
			String pay_cinema = cursor.getString(1);
			String pay_film = cursor.getString(2);
			String pay_showTime = cursor.getString(3);
			String pay_seats = cursor.getString(4);
			Sale sale = new Sale(pay_account, pay_cinema, pay_film, pay_showTime, pay_seats);
			saleList.add(sale);
		}
		
		return saleList;
	}
	
	
	
	//确定退票后,在Sales表中删除对应记录
	public static void deleteSale(String pay_account,String pay_cinema,String pay_film,String pay_showTime,String pay_seats){
		// 获取数据库对象
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
		
		database.delete("Sales", "payAccount=? and payCinema=? and payFilm=? and payShowTime=? and seats=?", 
				new String[]{pay_account, pay_cinema, pay_film, pay_showTime, pay_seats});
	} 
	
	
}
