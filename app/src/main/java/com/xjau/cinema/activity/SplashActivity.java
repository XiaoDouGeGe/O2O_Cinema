package com.xjau.cinema.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xjau.cinema.R;

public class SplashActivity extends Activity {
	
	protected static final int CODE_ENTER_LOGIN = 0;
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case CODE_ENTER_LOGIN:
				enterLogin();
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				copyDB("cinema.db");// 拷贝数据库
			}
		});
		
		
		
	} 
	
	private void enterLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
	
	
	/**
	 * 
	 * 拷贝数据库   把cinema.db数据库从assert文件夹拷贝到data/data/.../files目录下
	 * @param dbName
	 */
	private void copyDB(String dbName) {
		// File filesDir = getFilesDir();
		// System.out.println("路径:" + filesDir.getAbsolutePath());
		File destFile = new File(getFilesDir(), dbName);// 要拷贝的目标地址

		if (destFile.exists()) {
			System.out.println("数据库" + dbName + "已存在!");
//			mHandler.sendEmptyMessage(CODE_ENTER_HOME);// 延时5秒后发送消息
			mHandler.sendEmptyMessageDelayed(CODE_ENTER_LOGIN, 2000);// 延时2秒后发送消息
			return;
		}

		FileOutputStream out = null;
		InputStream in = null;

		try {
			in = getAssets().open(dbName);
			out = new FileOutputStream(destFile);

			int len = 0;
			byte[] buffer = new byte[1024];

			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				mHandler.sendEmptyMessage(CODE_ENTER_LOGIN);
//				mHandler.sendEmptyMessageDelayed(CODE_ENTER_HOME, 2000);// 延时2秒后发送消息
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
