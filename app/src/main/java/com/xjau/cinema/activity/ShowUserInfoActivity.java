package com.xjau.cinema.activity;

import java.util.ArrayList;

import com.xjau.cinema.R;
import com.xjau.cinema.activity.RefundActivity.MySalesListAdapter;
import com.xjau.cinema.db.dao.SalesDao;
import com.xjau.cinema.domain.Sale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ShowUserInfoActivity extends Activity{
	
	Button rebackToSelect;
	Button userInfoUpdate;
	TextView accountInfo;
	ListView lvSales;
	ArrayList<Sale> sales;
	private String userInfoAccount;
	private String preSaleAccount;
	private String preSaleCinema;
	private String preSaleFilm;
	private String preSaleShowTime;
	private String preSaleSeats;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_user_info);
		
		rebackToSelect = (Button) findViewById(R.id.btn_rebackToSelect);
		rebackToSelect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ShowUserInfoActivity.this,SelectBuyOrRefund.class);
				startActivity(intent);
			}
		});
		
		accountInfo = (TextView) findViewById(R.id.userInfoAccount);
		
		userInfoUpdate = (Button) findViewById(R.id.userInfoUpdate);
		userInfoUpdate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ShowUserInfoActivity.this,UserInfoUpdateActivity.class);
				startActivity(intent);
			}
		});
		
		
		
		userInfoAccount = LoginActivity.textAccount;
		sales =  SalesDao.getSaleList(userInfoAccount);
		
		accountInfo.setText("您的账号为：" + userInfoAccount);
		
		lvSales = (ListView) findViewById(R.id.lv_sales);
		lvSales.setAdapter(new MySalesListAdapter());
		
		//设置lvSales的Item的监听,点击了之后弹出单选框
		lvSales.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				preSaleAccount = sales.get(position).getPayAccout();
				preSaleCinema = sales.get(position).getPayCinema();
				preSaleFilm = sales.get(position).getPayFilm();
				preSaleShowTime = sales.get(position).getPayShowTime();
				preSaleSeats = sales.get(position).getSeats();

			}
		});
	}
	
	class MySalesListAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return sales.size();
		}
		@Override
		public Object getItem(int position) {
			return sales.get(position).toString();
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(ShowUserInfoActivity.this,
					R.layout.sale_list_item, null);
			TextView saleInfo = (TextView) view.findViewById(R.id.sale_info);
			
			saleInfo.setText(sales.get(position).toString());

			return view;
		}
		
	}
	
}
