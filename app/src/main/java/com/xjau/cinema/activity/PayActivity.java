package com.xjau.cinema.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xjau.cinema.R;
import com.xjau.cinema.db.dao.SalesDao;
import com.xjau.cinema.db.dao.SeatDao;



public class PayActivity extends Activity {
	
	Button rebackToSelect2;
	
	List<Integer> selectSeatList;
	
	TextView tvFilm;
	TextView tvSeat;
	TextView tvShowTime;
	Button pay;
	
	String textSeat = "";
	
	String payCinema;
	String payFilm;
	String payShowTime;
	
	String payAccount = LoginActivity.textAccount;  //购买的用户
	
	int seatNum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
	
		rebackToSelect2 = (Button) findViewById(R.id.btn_rebackToSelect2);
		rebackToSelect2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PayActivity.this,SelectBuyOrRefund.class);
				startActivity(intent);
			}
		});
		
		
		payCinema = ChooseFilmActivity.selectedCinemaName;
		payFilm = ShowDetailedInformation.detailedFilmName;
		payShowTime = ChooseFilmActivity.showTime;
		
		tvFilm = (TextView) findViewById(R.id.tv_film);
		tvSeat = (TextView) findViewById(R.id.tv_seat);
		tvShowTime = (TextView) findViewById(R.id.tv_showTime);
		pay = (Button) findViewById(R.id.btn_pay);
		
		// 赋值 电影
		tvFilm.setText(payCinema + "."+ payFilm);
		
		// 赋值 时间
		tvShowTime.setText(payShowTime);
		
		//下面几行代码，是显示座位号的。
		selectSeatList = getIntent().getIntegerArrayListExtra("seatList");
		
		for(int i=0;i<selectSeatList.size();i++){
			textSeat += (selectSeatList.get(i)+"座.");
		}
		
		tvSeat.setText(textSeat);
		
		//执行按钮pay
		pay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(PayActivity.this, "付款成功", 1).show();
				
				//付款成功之后，将这些信息存到数据库中
				SalesDao.insertSales(payAccount, payCinema, payFilm, payShowTime, textSeat);
				
				//付款成功之后，将数据库中的status表中的 0—>1
				for(int i=0;i<selectSeatList.size();i++){
					seatNum = selectSeatList.get(i);
					SeatDao.setSeatStatus(payFilm,seatNum);
				}
				
			}
		});
		
		
		
	}
	
}
