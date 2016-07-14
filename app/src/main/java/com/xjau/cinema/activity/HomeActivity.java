package com.xjau.cinema.activity;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.xjau.cinema.R;
import com.xjau.cinema.db.dao.CinemaDao;
import com.xjau.cinema.domain.Cinema;

public class HomeActivity extends Activity {
	
	private static final String[] sheng = {"北京"};
	private static final String[] qu = {"朝阳区","海淀区","西城区","东城区"};
	
	String[] areaName = {"望京","三里屯","工体","北京东站","朝阳公园"};
	private static final String[] area0 = {"望京","三里屯","工体","北京东站","朝阳公园"};
	private static final String[] area1 = {"大钟寺","五棵松","学院路","西三旗","北下关"};
	private static final String[] area2 = {"新街口","复兴门","菜市口","宣武门","什刹海"};
	private static final String[] area3 = {"东直门","王府井","雍和宫","天坛","和平里"};
	
	private Spinner spinner1;
	private Spinner spinner2;
	private Spinner spinner3;
	
	private ArrayAdapter<String> adapter1;
	private ArrayAdapter<String> adapter2;
	private ArrayAdapter<String> adapter3;
	
	private GridView gvFilm;
	private ListView lvCinema;
	
	ArrayList<Cinema> cinemas;
	
//	private int[] mPics = new int[] { R.drawable.zuoer, R.drawable.heyi, R.drawable.shenhua,
//			R.drawable.fenshou, R.drawable.xiju, R.drawable.congcong};
	private int[] mPics = new int[] { R.drawable.zuo, R.drawable.he, R.drawable.shen,
			R.drawable.fen, R.drawable.xi, R.drawable.cong};
	
//	private String[] mFilmName = new String[] {"左耳","何以笙箫默","神话","分手大师","喜剧之王","匆匆那年"};
//	
//	private String[] mInfo = new String[] {"单纯美丽的李珥左耳失聪，无法听见声音，然而，生理上的缺陷并没有令她感到自卑，正相反，她的个性温顺又温柔。一次偶然中…",
//			"大学时代的赵默笙，对C大法学系大才子何以琛一见倾心，开朗直率的她“死缠烂打”地倒追，与众不同的方式吸引了以琛的目光，一段纯纯的校园爱情悄悄滋生…",
//			"骁勇善战的秦朝大将军蒙毅（成龙饰）受秦始皇所命，负责护送玉漱公主（金喜善饰）入秦为妃，一路上彼此情愫暗生，可蒙毅还是选择了效忠君主…",
//			"一个是茫茫人海一人渣，一个是光盘贩子一支花，以吃软饭为生的梅远贵，和以卖成功学光盘为业的叶小春，两个底层男女在一次意外的英雄救美中相识…",
//			"尹天仇（周星驰饰）一直醉心戏剧，想成为一名演员，平时除了做跑龙套以外，还会在街坊福利会里开设演员训练班…",
//			"讲述了阳光少年陈寻、痴心女孩方茴、纯情备胎赵烨、温情暖男乔然、豪放女神林嘉茉这群死党跨越十五年的青春、记忆与友情…"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		/**
		 * spinner1
		 */
		spinner1 = (Spinner) findViewById(R.id.spinner_sheng);
		adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sheng);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter1);
		
		
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				String 	shengyu = sheng[position];
				arg0.setVisibility(View.VISIBLE);
				Log.v("dpw", "现在的省份是：" + shengyu);
			}		
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		/**
		 * spinner2
		 */
		spinner2 = (Spinner) findViewById(R.id.spinner_qu);
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, qu);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);
		
		spinner3 = (Spinner) findViewById(R.id.spinner_areaName);
		
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				
				/**
				 * spinner3  ：把spinner3放在spinner2中,产生级联。
				 */
				if (position == 0) {
					areaName = area0;
					adapter3 = new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item,area0);
				}
				if (position == 1) {
					areaName = area1;
					adapter3 = new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item,area1);
				}
				if (position == 2) {
					areaName = area2;
					adapter3 = new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item,area2);
				}
				if (position == 3) {
					areaName = area3;
					adapter3 = new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item,area3);
				}
				adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner3.setAdapter(adapter3);
				
				spinner3.setOnItemSelectedListener(new OnItemSelectedListener(){
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						String 	area = areaName[arg2];
						arg0.setVisibility(View.VISIBLE);
						
						Log.v("dpw", "现在的地理位置是："+area);
						
						//通过area获取cinema的信息列表
						cinemas =  CinemaDao.getCinemaList(area);
						
						
						/**
						 * ListView的cinema信息
						 * 
						 */
						lvCinema = (ListView) findViewById(R.id.lv_cinema);
						lvCinema.setAdapter(new MyListAdapter());
					
						//设置Item的监听
						lvCinema.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
								//获取选中的cinema条目，使用putExtra()方法封装在intent的中，启动下一个Activity.
								String cinemaName = cinemas.get(position).getCinema_name();
								Intent intent = new Intent(HomeActivity.this,ChooseFilmActivity.class);
								intent.putExtra("cinemaName", cinemaName);
								Log.v("cinemaName", "选中的电影院是"+cinemaName+"，进入下一个页面");
								startActivity(intent);
							}
						});
							
					}
					
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {}
				});
									
			}			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
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
			convertView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.pic_list_item, null);
			ImageView ivFilmPic = (ImageView) convertView.findViewById(R.id.iv_filmPic);
			ivFilmPic.setImageResource(mPics[position]);
			return convertView;
		}
		
	}
	
	/**
	 * ListView的cinema信息
	 * 
	 * @author Administrator
	 *
	 */
	class MyListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return cinemas.size();
		}

		@Override
		public Object getItem(int position) {
			return cinemas.get(position).toString();
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		
		//这就是ListView的条目：cinemas.get(position).toString()
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(HomeActivity.this,
					R.layout.cinema_list_item, null);
			TextView cinemaInfo = (TextView) view.findViewById(R.id.cinema_info);
			
			cinemaInfo.setText(cinemas.get(position).toString());
			
//			cinemaInfo.setTag(cinemas.get(position).getCinema_name());
			
			return view;
		}
	}	
}
