package com.xjau.cinema.activity;

import com.xjau.cinema.R;
import com.xjau.cinema.db.dao.AdminDao;
import com.xjau.cinema.db.dao.UserDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends Activity {

	EditText etAdminAccount;
	EditText etAdminPassword;
	Button btnAdminLogin;
	String textAdminAccount; 
	String textAdminPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_login);
		
		etAdminAccount = (EditText) findViewById(R.id.ad_account);
		etAdminPassword = (EditText) findViewById(R.id.ad_pwd);
		btnAdminLogin = (Button) findViewById(R.id.ad_login);
		
		//管理员登录按钮
		btnAdminLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				textAdminAccount = etAdminAccount.getText().toString();
				textAdminPassword = etAdminPassword.getText().toString();
				
				//登录
				if (AdminDao.checkAccount(textAdminAccount,textAdminPassword)) {      //登录成功
					Toast.makeText(AdminLoginActivity.this, "管理员登录成功", 1).show();
					Intent intent = new Intent(AdminLoginActivity.this, AdminManageActivity.class);
					startActivity(intent);
					finish();
				}else {               //登录失败
					Toast.makeText(AdminLoginActivity.this, "管理员账号或密码不正确", 1).show();
				}
				
			}
		});
	}
	
}
