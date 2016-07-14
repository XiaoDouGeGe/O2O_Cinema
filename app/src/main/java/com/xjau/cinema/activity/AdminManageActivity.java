package com.xjau.cinema.activity;

import com.xjau.cinema.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminManageActivity extends Activity {
	
	Button manageUser;
	Button manageSale;
	Button exit;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_manage);
		
		manageUser = (Button) findViewById(R.id.manage_user);
		manageSale = (Button) findViewById(R.id.manage_sale);
		exit = (Button) findViewById(R.id.exit);
		
		manageUser.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdminManageActivity.this, AdminManageUserActivity.class);
				startActivity(intent);
			}
		});
		
		manageSale.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdminManageActivity.this, AdminManageSaleActivity.class);
				startActivity(intent);
			}
		});
		
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdminManageActivity.this, LoginActivity.class);
				startActivity(intent); 
			}
		});
		
	}
}
