package com.xjau.cinema.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xjau.cinema.R;
import com.xjau.cinema.view.SeatView;
import com.xjau.cinema.view.SeatView.ZoomChangeListener;

public class SelectSeatActivity extends Activity {

	public static int ROW =10;// 设置最大列数
	public static int COL = 10;// 设置最大行数
 
	private ProgressDialog proDialog;
	private TextView yingmuTextView;
	int p1;
	public static int width;
	public static int height;
	StringBuilder seat = new StringBuilder();
	List<Integer> buySeatList = new ArrayList<Integer>();
	SeatView seatView;

	//private List<Integer> mySelectSeatList;// 保存选中座位
	public static List<Integer> seatList;   // 保存选中座位
	
	public  static String filmSeat; //其实还是之前的电影名称

	
	Button submitSeat;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_seat);
		
		//拿到Show页面传递过来的detailedFilmName参数（电影名称），从而来根据电影名称调用SeatDao方法查找座位。
		filmSeat = getIntent().getExtras().getString("FilmSeat");
		
		initView();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		width=(displayMetrics.widthPixels);
		height=(displayMetrics.heightPixels);
		new Thread(runnable).start();

		submitSeat = (Button) findViewById(R.id.submit_seat_btn);
		submitSeat.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(SelectSeatActivity.this,PayActivity.class);
				intent.putIntegerArrayListExtra("seatList", (ArrayList<Integer>) seatList);
				startActivity(intent);
			}
		});
		
	}

	private void initView() {
		yingmuTextView = (TextView) findViewById(R.id.yingmu);
		yingmuTextView.setText("测试影院" + " 测试厅" + " 荧幕");
		proDialog = ProgressDialog.show(SelectSeatActivity.this, "加载",
				"加载数据中，请稍候...", true, true);
		proDialog.setCanceledOnTouchOutside(false);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.arg1) {
			case 1:
				proDialog.dismiss();
				updateUI();
				break;
			}
		}
	};

	/**
	 * 显示所有座位
	 */
	private void updateUI() {
		LinearLayout myView = (LinearLayout) findViewById(R.id.seatviewcont);
		myView.removeAllViews();
		seatView = new SeatView(this);
		myView.addView(seatView);
		seatView.setZoomChangeListener(new ZoomChangeListener() {
			public void ZoomChange(int box_size) {
				LinearLayout myView2 = (LinearLayout) findViewById(R.id.seatraw);
				myView2.removeAllViews();
				for (int i = 0; i < COL; i++) {
					TextView textView = new TextView(
							SelectSeatActivity.this);
					textView.setText((i + 1) + "");
					textView.setTextSize(9.0f);
					textView.setGravity(Gravity.CENTER_VERTICAL);
					textView.setLayoutParams(new LayoutParams(
							LayoutParams.WRAP_CONTENT, box_size));
					myView2.addView(textView);
				}
			}
		});
		
		//mySelectSeatList = seatView.seatList;
	}

	Runnable runnable = new Runnable() {
		public void run() {
			Message msg = handler.obtainMessage();
			msg.arg1 = 1;
			handler.sendMessage(msg);
		}
	};

	
	
}
