package com.xjau.cinema.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xjau.cinema.R;
import com.xjau.cinema.db.dao.FilmDao;

public class ChooseFilmActivity extends Activity {
	
	private GridView gvFilm;
	private int[] mPics = new int[] { R.drawable.zuo, R.drawable.he, R.drawable.shen,
			R.drawable.fen, R.drawable.xi, R.drawable.cong};
	
	LinearLayout ll1;
	LinearLayout ll2;
	LinearLayout ll3;
	LinearLayout ll4;
	LinearLayout ll5;
	LinearLayout ll6;
	
	TextView selectedCinemaNameTextView;
	TextView filmName1;
	TextView filmName2;
	TextView filmName3;
	TextView filmName4;
	TextView filmName5;
	TextView filmName6;
	private int show1;
	private int show2;
	private int show3;
	private int show4;
	private int show5;
	private int show6;
	private String filmName;
	private String oneName;
	private String twoName;
	private String threeName;
	private String fourName;
	private String fiveName;
	private String sixName;
	
	private Intent intent;
	
	public static String selectedCinemaName;//电影院名称
	
	public static String showTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_film);
		
		Log.v("ChooseFilmActivity", "创建ChooseFilmActivity");
		
		selectedCinemaName = getIntent().getExtras().getString("cinemaName");
		
		Log.v("ChooseFilmActivity", "创建ChooseFilmActivity!!!");
		
		selectedCinemaNameTextView = (TextView) findViewById(R.id.selectedCinemaName);
		selectedCinemaNameTextView.setText(selectedCinemaName);
		
		
		filmName1 = (TextView) findViewById(R.id.tv_filmName1);
		filmName2 = (TextView) findViewById(R.id.tv_filmName2);
		filmName3 = (TextView) findViewById(R.id.tv_filmName3);
		filmName4 = (TextView) findViewById(R.id.tv_filmName4);
		filmName5 = (TextView) findViewById(R.id.tv_filmName5);
		filmName6 = (TextView) findViewById(R.id.tv_filmName6);
		
		show1 = FilmDao.getFirstFilmID(selectedCinemaName);
		show2 = FilmDao.getSecondFilmID(selectedCinemaName);
		show3 = FilmDao.getThirdFilmID(selectedCinemaName);
		show4 = FilmDao.getFourFilmID(selectedCinemaName);
		show5 = FilmDao.getFiveFilmID(selectedCinemaName);
		show6 = FilmDao.getSixFilmID(selectedCinemaName);
		oneName = FilmDao.getFilmName(show1);
		twoName = FilmDao.getFilmName(show2);
		threeName = FilmDao.getFilmName(show3);
		fourName = FilmDao.getFilmName(show4);
		fiveName = FilmDao.getFilmName(show5);
		sixName = FilmDao.getFilmName(show6);
		//把电影的名字返回给界面
		filmName1.setText(oneName);
		filmName2.setText(twoName);
		filmName3.setText(threeName);
		filmName4.setText(fourName);
		filmName5.setText(fiveName);
		filmName6.setText(sixName);
		

		ll1=(LinearLayout) findViewById(R.id.ll1);
		ll1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v("lookDetailedInformation", "准备调用lookDetailedInformation方法");
				filmName = oneName;
				lookDetailedInformation(filmName);
				showTime = "今天09:00开始";
			}
		});
		
		ll2=(LinearLayout) findViewById(R.id.ll2);
		ll2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				filmName = twoName;
				lookDetailedInformation(filmName);
				showTime = "今天14:00开始";
			}
		});
		
		ll3=(LinearLayout) findViewById(R.id.ll3);
		ll3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				filmName = threeName;
				lookDetailedInformation(filmName);
				showTime = "今天19:00开始";
			}
		});
		
		ll4=(LinearLayout) findViewById(R.id.ll4);
		ll4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				filmName = fourName;
				lookDetailedInformation(filmName);
				showTime = "明天09:00开始";
			}
		});
		
		ll5=(LinearLayout) findViewById(R.id.ll5);
		ll5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				filmName = fiveName;
				lookDetailedInformation(filmName);
				showTime = "明天14:00开始";
			}
		});
		
		ll6=(LinearLayout) findViewById(R.id.ll6);
		ll6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				filmName = sixName;
				lookDetailedInformation(filmName);
				showTime = "明天19:00开始";
			}
		});
		
		
		/**
		 * GridView的电影图片列表
		 * 
		 */
		gvFilm = (GridView) findViewById(R.id.gv_film);
		/**
		 * 设置GirdView参数，绑定数据.这一段代码日了狗
		 */
		int size = mPics.length;
        int length = 120;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length -20) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gvFilm.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gvFilm.setColumnWidth(itemWidth); // 设置列表项宽
        gvFilm.setHorizontalSpacing(5); // 设置列表项水平间距
        gvFilm.setStretchMode(GridView.NO_STRETCH);
        gvFilm.setNumColumns(size); // 设置列数量=列表集合数

		gvFilm.setAdapter(new GridViewAdapter());
		
	}
	
	/**
	 * GridView的电影图片列表
	 * 
	 * @author Administrator
	 *
	 */
	class GridViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mPics.length;
		}

		@Override
		public Object getItem(int position) {
			return mPics[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = LayoutInflater.from(ChooseFilmActivity.this).inflate(R.layout.pic_list_item, null);
			ImageView ivFilmPic = (ImageView) convertView.findViewById(R.id.iv_filmPic);
			ivFilmPic.setImageResource(mPics[position]);
			return convertView;
		}
		
	}
	
	//跳转到ShowDetailedInformation页面。根据MyFilmName决定携带什么数据。
	public void lookDetailedInformation(String MyFilmName){
		Log.v("lookDetailedInformation", "成功调用lookDetailedInformation方法");
			
		intent = new Intent(ChooseFilmActivity.this, ShowDetailedInformation.class);
		intent.putExtra("DetailedFilmName", MyFilmName);
		startActivity(intent);
	}
	
}
