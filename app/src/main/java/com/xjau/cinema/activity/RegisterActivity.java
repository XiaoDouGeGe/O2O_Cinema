package com.xjau.cinema.activity;

import com.xjau.cinema.R;
import com.xjau.cinema.db.dao.UserDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	EditText regAccount;
	EditText regPassword;
	EditText regPasswordTwice;
	Button btnRegSumbit;
	Button btnBackToLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		regAccount = (EditText) findViewById(R.id.reg_account);
		regPassword = (EditText) findViewById(R.id.reg_pwd);
		regPasswordTwice = (EditText) findViewById(R.id.reg_pwd_twice);
		btnRegSumbit = (Button) findViewById(R.id.reg_submit);
		btnBackToLogin = (Button) findViewById(R.id.backToLogin);
		
		btnRegSumbit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//获取EditText的值
				String textRegAccount = regAccount.getText().toString();
				String textRegPassword = regPassword.getText().toString();
				String textRegPasswordTwice = regPasswordTwice.getText().toString();
				
				//1.判断这个账号textRegAccount有没有被注册，能否注册
				if (UserDao.isRegistered(textRegAccount)) {        //可以注册
					if(!(textRegPassword.equals(""))){  //密码不为空
						if(textRegPassword.equals(textRegPasswordTwice)){ //两次输入密码相同
							//注册成功
							Toast.makeText(RegisterActivity.this, "注册成功", 1).show();
							//存入数据库表User
							UserDao.insertUser(textRegAccount, textRegPassword);
						}else{Toast.makeText(RegisterActivity.this, "2次密码不一致", 1).show();}
					}else{Toast.makeText(RegisterActivity.this, "不能为空", 1).show();}
				}else { //已被注册
					Toast.makeText(RegisterActivity.this, "该账号已被注册，请重新输入", 1).show();
				}
			}
		});
		
		btnBackToLogin.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
