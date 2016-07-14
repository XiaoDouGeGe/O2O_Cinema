package com.xjau.cinema.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.xjau.cinema.R;
import com.xjau.cinema.activity.SelectSeatActivity;
import com.xjau.cinema.db.dao.SeatDao;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("DrawAllocation")
public class SeatView extends View {
	Paint paint = new Paint();
	Bitmap seat_ok;
	Bitmap seat_selled;
	Bitmap seat_select;
	Bitmap seat_null;
	int WIDTH, HEIGHT;
	int BOX_MAX_SIZE = 50;
	int SEAT_MAX_SIZE = 40;
	int box_size = BOX_MAX_SIZE;
	int seat_size = SEAT_MAX_SIZE;
	int hang = 0;
	int lie = 0;
	double zoom;
	double zoom_beilv;
	int zoom_level = 0;
	long mLastTime;
	long mCurTime;
	Canvas canvas;
	private List<Integer> mySeatList;// 保存选中座位
	//public static List<Integer> seatList;// seatList=mySeatList,保存选中座位,返回给SelectSeatActivity页面，从而传递给下一个页面
	private List<Integer> unavaliableSeatList;
	Bitmap SeatOk, SeatSelled, SeatSelect;
	boolean isFirstLoad = true;

	int status; //座位的状态
	int SEAT_ID;
	
	public SeatView(Context context) {
		super(context);
		mySeatList = new ArrayList<Integer>();
		unavaliableSeatList = new ArrayList<Integer>();
		lie = SelectSeatActivity.ROW;
		hang = SelectSeatActivity.COL;

		SeatOk = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.seat_ok);
		SeatSelled = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.seat_selled);
		SeatSelect = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.seat_select);
	}

	public SeatView(Context context, AttributeSet attr) {
		super(context, attr);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(box_size * lie, box_size * hang);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (isFirstLoad) {
			double zoom_temp1 = ((SelectSeatActivity.width * 1.0 - 100)
					/ lie / box_size);
			double zoom_temp2 = ((SelectSeatActivity.height * 1.0 /2.5)
					/ hang / box_size);
			if (zoom_temp1>zoom_temp2) {
				zoom_temp1=zoom_temp2;
			}
			zoom_beilv = Math.pow(1 / zoom_temp1, 1 / 4.0);
			box_size = (int) (box_size * zoom_temp1);
			seat_size = (int) (seat_size * zoom_temp1);
			OnZoomChangeListener.ZoomChange(box_size);
			isFirstLoad = false;
			zoom = 1.0;
			zoom_level = 1;
		}
		box_size = (int) (box_size * zoom);
		seat_size = (int) (seat_size * zoom);

		// 可购买座位
		seat_ok = Bitmap.createScaledBitmap(SeatOk, seat_size, seat_size, true);
		// 红色 已售座位
		seat_selled = Bitmap.createScaledBitmap(SeatSelled, seat_size,
				seat_size, true);
		// 绿色 我的选择
		seat_select = Bitmap.createScaledBitmap(SeatSelect, seat_size,
				seat_size, true);
		
		// 画座位
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < lie; j++) {
				
				//先把所有的座位都表示成“可购买”
				canvas.drawBitmap(seat_ok, j * (box_size), i * (box_size), null);
				
				SEAT_ID = i*10+j+1;
				
				//查找数据库中标Seat的status,从而决定画什么颜色的 图形
				status = SeatDao.getSeatStatus(SelectSeatActivity.filmSeat,SEAT_ID);
				
				if (status == 1) {
					canvas.drawBitmap(seat_selled, j * (box_size), i
							* (box_size), null);
					unavaliableSeatList.add(i * lie + j);
				}
			}
		}
		
		// 我的座位 变成绿色
		for (int i = 0; i < mySeatList.size(); i++) {
			canvas.drawBitmap(seat_select,
					(mySeatList.get(i) % lie) * box_size,
					(mySeatList.get(i) / lie) * box_size, null);
		}
		this.setLayoutParams(new LinearLayout.LayoutParams(box_size * lie,
				box_size * hang));
		zoom = 1.0;
	}

	int mode = 0;
	float oldDist;
	float newDist;
	int oldClick;
	int newClick;
	boolean zoomType;
	int count = 0;
	float currentXPosition;
	float currentYPosition;

	
	
	public boolean onTouchEvent(MotionEvent event) {

			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				count++;
				if (count == 1) {
					mLastTime = getClickTime(event);
					//Log.v("dpw", "选中的座位号是" + dpwNum);
				} else if (count == 2) {
					mCurTime = getClickTime(event);
					if (zoom_level == 1 && mCurTime - mLastTime < 400) {
						box_size = BOX_MAX_SIZE;
						seat_size = SEAT_MAX_SIZE;
						zoom = 1.0;
						zoom_level = 5;
						OnZoomChangeListener.ZoomChange(box_size);
						invalidate();
					}
					count = 0;
					mCurTime = 0;
					mLastTime = 0;
				}
				mode = 1;
				oldClick = getClickPoint(event);
				break;
			case MotionEvent.ACTION_UP:
				mode = 0;
				newClick = getClickPoint(event);
				//这边打印一个输出语句，可以输出newClick.
				Log.v("seat", newClick+"");
				if (newClick == oldClick) {
					if (mySeatList.contains(newClick)) {
						mySeatList.remove(mySeatList.indexOf(newClick));
						invalidate();
					} else if (!unavaliableSeatList.contains(newClick)) {
						mySeatList.add(newClick);
						invalidate();    //invalidate()是用来刷新View的，必须是在UI线程中进行工作。
					}
				}
				
				zoomType = false;
				break;
			case MotionEvent.ACTION_POINTER_UP:
				mode -= 1;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				count = 0;
				mode += 1;
				zoomType = true;
				oldDist = spacing(event);// 两点按下时的距离
				break;
			case MotionEvent.ACTION_MOVE:
				if (mode >= 2) {
					newDist = spacing(event);
					if (zoomType && newDist - oldDist > 10) {
						if (zoom_level == 4) {
							box_size = BOX_MAX_SIZE;
							seat_size = SEAT_MAX_SIZE;
							OnZoomChangeListener.ZoomChange(box_size);
							zoom = 1.0;
							zoom_level = 5;
							invalidate();
						} else if (zoom_level < 5) {
							zoom = zoom_beilv;
							OnZoomChangeListener
									.ZoomChange((int) (box_size * zoom));
							zoom_level++;
							invalidate();
						}
						zoomType = false;
					} else if (zoomType && oldDist - newDist > 10) {
						if (zoom_level > 1) {
							zoom = 1 / zoom_beilv;
							OnZoomChangeListener
									.ZoomChange((int) (box_size * zoom));
							zoom_level--;
							invalidate();
						}
						zoomType = false;
					}
				}
				break;
			}

		SelectSeatActivity.seatList = mySeatList;
		
		return true;

	}

	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return (float)Math.sqrt(x * x + y * y);
	}

	private int getClickPoint(MotionEvent event) {
		float currentXPosition = event.getX();
		float currentYPosition = event.getY();
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < lie; j++) {
				if ((j * box_size) < currentXPosition
						&& currentXPosition < j * box_size + box_size
						&& (i * box_size) < currentYPosition
						&& currentYPosition < i * box_size + box_size) {
					//dpwNum = i*10+j+1; //测试用的. dpw
					return i * 10 + j; //相当于返回的是seat_id
				}
			}
		}
		return 0;
	}

	private long getClickTime(MotionEvent event) {
		return System.currentTimeMillis();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		WIDTH = w;
		HEIGHT = h;
	}

	public interface ZoomChangeListener {
		public void ZoomChange(int box_size);
	}

	private ZoomChangeListener OnZoomChangeListener = null;
	//private int dpwNum;

	public void setZoomChangeListener(ZoomChangeListener listener) {
		OnZoomChangeListener = listener;
	}


}
