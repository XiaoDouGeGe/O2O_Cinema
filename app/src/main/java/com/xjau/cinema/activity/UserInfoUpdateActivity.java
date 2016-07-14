package com.xjau.cinema.activity;

import com.xjau.cinema.R;
import com.xjau.cinema.db.dao.UserDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoUpdateActivity extends Activity {
	String dangqianyonghuString;
	
	TextView dangqianyonghuTextView;
	EditText yuanmimaEditText;
	EditText xinmimaEditText;
	EditText xinmima_againEditText;
	Button submitUpdateButton;
	Button cancelUpdateButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_update);
		
		dangqianyonghuString = LoginActivity.textAccount;
		dangqianyonghuTextView = (TextView) findViewById(R.id.dangqianyonghu);
		dangqianyonghuTextView.setText("当前用户：" + dangqianyonghuString);
		
		yuanmimaEditText = (EditText) findViewById(R.id.yuanmima);
		xinmimaEditText = (EditText) findViewById(R.id.xinmima);
		xinmima_againEditText = (EditText) findViewById(R.id.xinmima_again);
		submitUpdateButton = (Button) findViewById(R.id.submitUpdate);
		cancelUpdateButton = (Button) findViewById(R.id.cancelUpdate);
		
		submitUpdateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (UserDao.checkAccount(dangqianyonghuString, yuanmimaEditText.getText().toString()) &&
						xinmimaEditText.getText().toString().equals(xinmima_againEditText.getText().toString())) {
					UserDao.updateUser(dangqianyonghuString, xinmima_againEditText.getText().toString());
					Toast.makeText(UserInfoUpdateActivity.this, "密码更改成功！请重新登录！", 1).show();
					//返回登录页面
					Intent intent = new Intent(UserInfoUpdateActivity.this,LoginActivity.class);
					startActivity(intent);
				}else {
					Toast.makeText(UserInfoUpdateActivity.this, "输入有误！", 1).show();
				}
				
			}
		});
		
		cancelUpdateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//返回个人中心页面
				Intent intent = new Intent(UserInfoUpdateActivity.this,SelectBuyOrRefund.class);
				startActivity(intent);
			}
		});
		
	}
}
