package com.xjau.cinema.activity;

import java.util.ArrayList;

import com.xjau.cinema.R;
import com.xjau.cinema.activity.RefundActivity.MySalesListAdapter;
import com.xjau.cinema.db.dao.AdminDao;
import com.xjau.cinema.db.dao.SalesDao;
import com.xjau.cinema.db.dao.UserDao;
import com.xjau.cinema.domain.Sale;
import com.xjau.cinema.domain.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AdminManageUserActivity extends Activity {

	ListView lvUser;
	ArrayList<User> users;
	private String preUserAccount;
	private String preUserPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_manage_user);
		
		users =  UserDao.getAllUserList();
		lvUser = (ListView) findViewById(R.id.lv_user);
		lvUser.setAdapter(new MyUsersListAdapter());
		
		//设置lvSales的Item的监听,点击了之后弹出单选框
		lvUser.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				//为下面的“删除”操作准备参数值
				preUserAccount = users.get(position).getAccount();
				preUserPassword = users.get(position).getPassword();

				AlertDialog.Builder builder = new AlertDialog.Builder(AdminManageUserActivity.this).
						setTitle("删除对话框").setMessage("确定将此用户删除？");
				//添加“确定”按钮
				setPositiveButton(builder);
				//添加“取消”按钮
				setNegativeButton(builder).create().show();
			}
		});
		
	}
	
	class MyUsersListAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return users.size();
		}
		@Override
		public Object getItem(int position) {
			return users.get(position).toString();
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(AdminManageUserActivity.this,
					R.layout.user_list_item, null);
			TextView userInfo = (TextView) view.findViewById(R.id.user_info);
			
			userInfo.setText(users.get(position).toString());

			return view;
		}
		
	}
	
	
	
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder){
		//调用setPositiveButton方法添加“确定”按钮
		return builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//点击“确定”后，(1)执行在数据库Sales中 执行删除操作;(2)将对应的座位号状态由1置为0;(3)对话框消失;(4)重新显示ListView
				//1.删除操作.
				UserDao.deleteUser(preUserAccount, preUserPassword);
				//2.将对应的座位号状态由1置为0
				//因为座位是数组形式，而在sales中是文本保存，难以逆向转化，所以暂不操作。可以理解为系统需要一定时间的进行调整，在退票瞬间座位不变。
				//3.对话框消失
				dialog.dismiss();
				Toast.makeText(AdminManageUserActivity.this, "删除成功", 1).show();
				//4.重新显示ListView---------跟onCreate()方法的代码重用了 
				users =  UserDao.getAllUserList();
				lvUser.setAdapter(new MyUsersListAdapter());
				lvUser.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder builder = new AlertDialog.Builder(AdminManageUserActivity.this).
								setTitle("删除对话框").setMessage("确定将此用户删除？");
						setPositiveButton(builder);
						setNegativeButton(builder).create().show(); 
					}
				});
			}
		});
	}
	
	private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder){
		return builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//点击“取消”后，对话框消失，不执行其它任何操作
				dialog.dismiss();
			}
		});
		
	}
	
}
