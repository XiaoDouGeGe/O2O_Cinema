package com.xjau.cinema.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xjau.cinema.R;
import com.xjau.cinema.db.dao.SalesDao;
import com.xjau.cinema.domain.Sale;

public class AdminManageSaleActivity extends Activity {

	
	ListView lvSales;
	ArrayList<Sale> sales;
	//private Bundle state;
	private String refundAccount;
	
	private String preSaleAccount;
	private String preSaleCinema;
	private String preSaleFilm;
	private String preSaleShowTime;
	private String preSaleSeats;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_manage_sale);
		//state = savedInstanceState;
		
		
		//refundAccount = LoginActivity.textAccount;
		//sales =  SalesDao.getSaleList(refundAccount);
		sales =  SalesDao.getAllSaleList();
		
		lvSales = (ListView) findViewById(R.id.lv_sales);
		lvSales.setAdapter(new MySalesListAdapter());
		
		//设置lvSales的Item的监听,点击了之后弹出单选框
		lvSales.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				//为下面的“删除”操作准备参数值
				preSaleAccount = sales.get(position).getPayAccout();
				preSaleCinema = sales.get(position).getPayCinema();
				preSaleFilm = sales.get(position).getPayFilm();
				preSaleShowTime = sales.get(position).getPayShowTime();
				preSaleSeats = sales.get(position).getSeats();

				AlertDialog.Builder builder = new AlertDialog.Builder(AdminManageSaleActivity.this).
						setTitle("删除对话框").setMessage("确定将此售票记录删除？");
				//添加“确定”按钮
				setPositiveButton(builder);
				//添加“取消”按钮
				setNegativeButton(builder).create().show();
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
			View view = View.inflate(AdminManageSaleActivity.this,
					R.layout.sale_list_item, null);
			TextView saleInfo = (TextView) view.findViewById(R.id.sale_info);
			
			saleInfo.setText(sales.get(position).toString());

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
				SalesDao.deleteSale(preSaleAccount, preSaleCinema, preSaleFilm, preSaleShowTime, preSaleSeats);
				//2.将对应的座位号状态由1置为0
				//因为座位是数组形式，而在sales中是文本保存，难以逆向转化，所以暂不操作。可以理解为系统需要一定时间的进行调整，在退票瞬间座位不变。
				//3.对话框消失
				dialog.dismiss();
				Toast.makeText(AdminManageSaleActivity.this, "退票成功", 1).show();
				//4.重新显示ListView---------跟onCreate()方法的代码重用了 
				sales =  SalesDao.getAllSaleList();
				lvSales.setAdapter(new MySalesListAdapter());
				lvSales.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder builder = new AlertDialog.Builder(AdminManageSaleActivity.this).
								setTitle("退票对话框").setMessage("确定将此票退回？");
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
