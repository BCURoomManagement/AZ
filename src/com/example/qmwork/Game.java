package com.example.qmwork;

import android.view.SurfaceView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

public class Game extends SurfaceView implements Callback, Runnable {
	//障碍物
	private int x1, y1;
	private int x2, y2;
	private int x3, y3;
	private int x4, y4;
	private Bitmap zaw1, zaw2, zaw3,zaw4;
	//小球
	private int x, y;
	private GameActivity activity;
	private Paint paint;
	private SurfaceHolder holder;
	private boolean isCollsion; // 标识是否发生碰撞
	private Canvas canvas;
	public int count;//计分
	private Thread th;
	private boolean flag;
	
	public void initBitmap() {
	}
	public void myDraw() {
		try {
			canvas = holder.lockCanvas();
			canvas.drawColor(Color.parseColor("#acd6ff")); // 绘制背景色
			canvas.save();
			paint.setColor(Color.rgb(235, 0, 1));
			paint.setTextSize(35);
			canvas.drawText("分数:" + String.valueOf(count), 30, 40, paint); // 绘制文字分数
			canvas.restore();
			// 判断小鸟的初始位置
						if (y1 > 0) {
							zaw1();
						}
						if (y2 > 0) {
							zaw2();
						}
						if (y3 > 0) {
							zaw3();
						}
						if (y4 > 0) {
							zaw4();
						}


						canvas.drawBitmap(zaw1, x1, y1, paint);
						canvas.drawBitmap(zaw2, x2, y2, paint);
						canvas.drawBitmap(zaw3, x3, y3, paint);
						canvas.drawBitmap(zaw4, x4, y4, paint);
			canvas.save();
		} catch (Exception e) {
		} finally {

			holder.unlockCanvasAndPost(canvas);
		}
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		initBitmap(); // 初始化图片资源
		flag = true;
		th = new Thread(this); // 实例线程
		th.start(); // 启动线程
	}
	public Game(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		activity = (GameActivity) context;
		holder = getHolder();
		holder.addCallback(this);
		paint = new Paint(); // 实例化画笔
		paint.setColor(Color.BLACK);
		setFocusable(true);
		// player1=MediaPlayer.create(context, R.raw.explosion);
		activity = (GameActivity) context;
		zaw1 = BitmapFactory.decodeResource(getResources(), R.drawable.zaw);
		zaw2 = BitmapFactory.decodeResource(getResources(), R.drawable.zaw2);
		zaw3 = BitmapFactory.decodeResource(getResources(), R.drawable.zaw3);
		zaw4 = BitmapFactory.decodeResource(getResources(), R.drawable.zaw4);
		x1 = 50;
		y1 = 150; // 飞机的初始位置
		x2 = 130;
		y2 = 50;
		x3 = 80;
		y3 = 230;
		x4 = 100;
		y4 = 100;
	}
	private void replace() {
		if (y1 > this.getHeight()) {
			y1 = (int) (1 + Math.random() * (10 - 1 + 1));
			count += 10;
			canvas.drawBitmap(zaw1, x1, y1, paint);
		} else if (y2 > this.getHeight()) {
			y2 = (int) (10 + Math.random() * (10 - 1 + 1));
			count += 10;
			canvas.drawBitmap(zaw2, x2, y2, paint);
		} else if (y3 > this.getHeight()) {
			y3 = (int) (20 + Math.random() * (10 - 1 + 1));
			count += 10;
			canvas.drawBitmap(zaw3, x3, y3, paint);
		}
		else if (y4 > this.getHeight()) {
			y4 = (int) (20 + Math.random() * (10 - 1 + 1));
			count += 10;
			canvas.drawBitmap(zaw4, x4, y4, paint);
		}
	}
	private void zaw1() {
		y1 += (int) (1 + Math.random() * (1 - 1 + 1));
		replace();
	}

	private void zaw2() {
		y2 += (int) (10 + Math.random() * (10 - 1 + 1));
		replace();
	}

	private void zaw3() {
		y3 += (int) (18 + Math.random() * (10 - 1 + 1));
		replace();
	}
	private void zaw4() {
		y4 += (int) (18 + Math.random() * (10 - 1 + 1));
		replace();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		myDraw();
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
