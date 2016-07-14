package com.xjau.cinema.activity;

import com.xjau.cinema.R;
import com.xjau.cinema.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectBuyOrRefund extends Activity {

	Button showUserInfo;
	Button selectToBuy;
	Button selectToRefund;
	Button exit;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_buy_or_refund);
		
		showUserInfo = (Button) findViewById(id.btn_showUserInfo);
		selectToBuy = (Button) findViewById(id.selectBuy);
		selectToRefund = (Button) findViewById(id.selectRefund);
		exit = (Button) findViewById(id.exit);
		
		//给showUserInfo绑定按钮，跳转到HomeActivity页面
		showUserInfo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectBuyOrRefund.this, ShowUserInfoActivity.class);
				startActivity(intent);
			}
		});
		
		//给selectToBuy绑定按钮，跳转到HomeActivity页面
		selectToBuy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectBuyOrRefund.this, HomeActivity.class);
				startActivity(intent);
			}
		});
		
		selectToRefund.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectBuyOrRefund.this, RefundActivity.class);
				startActivity(intent); 
			}
		});
		
		
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectBuyOrRefund.this, LoginActivity.class);
				startActivity(intent); 
			}
		});
	}
	
}
