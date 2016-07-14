package com.xjau.cinema.activity;

import com.xjau.cinema.R;
import com.xjau.cinema.db.dao.UserDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText etAccount;
	EditText etPassword;
	Button btnLogin;
	Button btnRegister;
	Button btnChangeToAdminLogin;
	
	public static String textAccount; //这样声明是为了在最后的PayActivity能够获取到
	String textPassword;
	
	protected static final int CODE_ENTER_HOME = 0;
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case CODE_ENTER_HOME:
				enterHome();
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		etAccount = (EditText) findViewById(R.id.account);
		etPassword = (EditText) findViewById(R.id.pwd);
		btnLogin = (Button) findViewById(R.id.login);
		btnRegister = (Button) findViewById(R.id.register);
		btnChangeToAdminLogin = (Button) findViewById(R.id.changeToAdminLogin);
		
		//登录按钮
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				textAccount = etAccount.getText().toString();
				textPassword = etPassword.getText().toString();
				
				//登录
				if (UserDao.checkAccount(textAccount,textPassword)) {      //登录成功
					Toast.makeText(LoginActivity.this, "登录成功", 1).show();
					mHandler.sendEmptyMessageDelayed(CODE_ENTER_HOME, 2000);// 延时2秒后发送消息
				}else {               //登录失败
					Toast.makeText(LoginActivity.this, "账号或密码不正确", 1).show();
				}
				
			}
		});
		
		
		//注册按钮
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
	
		btnChangeToAdminLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this, AdminLoginActivity.class);
				startActivity(intent);
			}
		});
		
	}

	
	
	private void enterHome() {
		Intent intent = new Intent(this, SelectBuyOrRefund.class);
		startActivity(intent);
		finish();
	}
}
