package com.xjau.cinema.activity;

import com.xjau.cinema.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowDetailedInformation extends Activity {

	ImageView bigPic;
	TextView introduction;
	Button xuanzuo;
	
	public static String detailedFilmName; 

//	{"单纯美丽的李珥左耳失聪，无法听见声音，然而，生理上的缺陷并没有令她感到自卑，正相反，她的个性温顺又温柔。一次偶然中…",
//		"大学时代的赵默笙，对C大法学系大才子何以琛一见倾心，开朗直率的她“死缠烂打”地倒追，与众不同的方式吸引了以琛的目光，一段纯纯的校园爱情悄悄滋生…",
//		"骁勇善战的秦朝大将军蒙毅（成龙饰）受秦始皇所命，负责护送玉漱公主（金喜善饰）入秦为妃，一路上彼此情愫暗生，可蒙毅还是选择了效忠君主…",
//		"一个是茫茫人海一人渣，一个是光盘贩子一支花，以吃软饭为生的梅远贵，和以卖成功学光盘为业的叶小春，两个底层男女在一次意外的英雄救美中相识…",
//		"尹天仇（周星驰饰）一直醉心戏剧，想成为一名演员，平时除了做跑龙套以外，还会在街坊福利会里开设演员训练班…",
//		"讲述了阳光少年陈寻、痴心女孩方茴、纯情备胎赵烨、温情暖男乔然、豪放女神林嘉茉这群死党跨越十五年的青春、记忆与友情…"}
	
	
	String introduct_zuo = "单纯美丽的李珥左耳失聪，无法听见声音，然而，生理上的缺陷并没有令她感到自卑，正相反，她的个性温顺又温柔。一次偶然中…";
	String introduct_he = "大学时代的赵默笙，对C大法学系大才子何以琛一见倾心，开朗直率的她“死缠烂打”地倒追，与众不同的方式吸引了以琛的目光，一段纯纯的校园爱情悄悄滋生…";
	String introduct_shen = "骁勇善战的秦朝大将军蒙毅（成龙饰）受秦始皇所命，负责护送玉漱公主（金喜善饰）入秦为妃，一路上彼此情愫暗生，可蒙毅还是选择了效忠君主…";
	String introduct_fen = "一个是茫茫人海一人渣，一个是光盘贩子一支花，以吃软饭为生的梅远贵，和以卖成功学光盘为业的叶小春，两个底层男女在一次意外的英雄救美中相识…";
	String introduct_xi = "尹天仇（周星驰饰）一直醉心戏剧，想成为一名演员，平时除了做跑龙套以外，还会在街坊福利会里开设演员训练班…";
	String introduct_cong = "讲述了阳光少年陈寻、痴心女孩方茴、纯情备胎赵烨、温情暖男乔然、豪放女神林嘉茉这群死党跨越十五年的青春、记忆与友情…";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_detailed_information);
		
		detailedFilmName = getIntent().getExtras().getString("DetailedFilmName");
		Log.v("ShowDetailedInformation", detailedFilmName);

		bigPic = (ImageView) findViewById(R.id.iv_bigPic);
		introduction = (TextView) findViewById(R.id.tv_introduction);
		
		if(detailedFilmName.equals("左耳")){
			Log.v("if", "能够设置左耳");
			bigPic.setImageResource(R.drawable.zuo);
			introduction.setText(introduct_zuo);
		}
		else if(detailedFilmName.equals("何以笙箫默")){
			Log.v("if", "能够设置何以笙箫默");
			bigPic.setImageResource(R.drawable.he);
			introduction.setText(introduct_he);
		}
		else if(detailedFilmName.equals("神话")){
			bigPic.setImageResource(R.drawable.shen);
			introduction.setText(introduct_shen);
		}
		else if(detailedFilmName.equals("分手大师")){
			bigPic.setImageResource(R.drawable.fen);
			introduction.setText(introduct_fen);
		}
		else if(detailedFilmName.equals("喜剧之王")){
			bigPic.setImageResource(R.drawable.xi);
			introduction.setText(introduct_xi);
		}
		else if(detailedFilmName.equals("匆匆那年")){
			bigPic.setImageResource(R.drawable.cong);
			introduction.setText(introduct_cong);
		}
		
		xuanzuo = (Button) findViewById(R.id.btn_xuanzuo);
		xuanzuo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v("seat", "查找"+detailedFilmName+"的座位");
				Intent intent = new Intent(ShowDetailedInformation.this,SelectSeatActivity.class);
				intent.putExtra("FilmSeat", detailedFilmName);
				startActivity(intent);
			}
		});
		
	}
	
	
	
}
