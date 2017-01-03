package com.example.testmovetolayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 仿高德地图开始运动顶部布局随手指移动放大缩小
 * @author 莫筱晴
 *
 */
public class MainActivity extends Activity implements OnTouchListener{
	private TextView tv_top;
	private TextView tv_left;
	private TextView tv_right;
	private TextView tv_bottom;
	private RelativeLayout root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_top = (TextView) findViewById(R.id.tv_top);
		tv_left = (TextView) findViewById(R.id.tv_left);
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_bottom = (TextView) findViewById(R.id.tv_bottom);
		
		root = (RelativeLayout) findViewById(R.id.root);
		root.setOnTouchListener(this);
		rootHeight = root.getLayoutParams().height;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	  private int  lastY,lastY2,lastY3;
	  private float topSize = 20,leftSize = 15,bottomSize = 10;
	  private int rootHeight;
	@Override
	public boolean onTouch(View view, MotionEvent event) {
         switch (event.getAction() & MotionEvent.ACTION_MASK) {  
         case MotionEvent.ACTION_DOWN:  
        	
             lastY = (int) event.getRawY();
             lastY2 = (int) event.getRawY();
             Log.i("ontouch", "down》》》》》》》》》》》》》"+lastY);
             break;  
         case MotionEvent.ACTION_UP:  
        	 int dy1 = (int) event.getRawY() - lastY2;//上下移动了多少个单位
        	 Log.i("ontouch", "up》》》》》》》》》》》》》"+dy1+" absMath:>"+Math.abs(dy1));
        	
            	 
           
             break;  
         case MotionEvent.ACTION_POINTER_DOWN:  
        	
             break;  
         case MotionEvent.ACTION_POINTER_UP:  
        	
             break;  
         case MotionEvent.ACTION_MOVE:  
        	 int dy = (int) event.getRawY() - lastY;
        	 int top = view.getTop() + dy;
        	 Log.i("ontouch", "move》》》》》》》》》》》》》"+top+"dy:"+dy+"lastY"+lastY+"now:"+event.getRawY());
          /*
        	 int dx = (int) event.getRawX() - lastX;
             int left = view.getLeft() + dx;
             RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view 
                     .getLayoutParams();  
             layoutParams.height=height;
             layoutParams.width = view.getWidth();
             layoutParams.leftMargin =0;  
             layoutParams.topMargin =top;  
             root.setLayoutParams(layoutParams);
             root.layout(0, top,0, 0);
             lastX = (int) event.getRawX();
             lastY = (int) event.getRawY();*/
//             moveViewWithFinger(root, 0, top, null);
//        	 root.layout(0, top, root.getWidth(), root.getHeight());
//        	 if(top >0&&top<height-handleHeight)
//        	 root_1.moveViewWithFinger(root, 0, 0, 0,dy,false);
        	 int nowHeight = root.getHeight()+dy;
        	 int nowTop = tv_top.getTop()+dy/8;
        	 if(nowTop<30)nowTop = 30;
        	 if(nowHeight>=rootHeight){
        	
        	 RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view 
                     .getLayoutParams();  
        	 layoutParams.height = nowHeight;
        	 
        	 root.setLayoutParams(layoutParams);
        	 RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) tv_top 
                     .getLayoutParams();  
        	 layoutParams2.height = tv_top.getHeight()+dy/2;
        	 layoutParams2.topMargin = nowTop;
        	 tv_top.setLayoutParams(layoutParams2);
        	/* RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) tv_left 
                     .getLayoutParams();  
        	 layoutParams3.height = tv_left.getHeight()+dy/4;*/
//        	 layoutParams3.addRule(RelativeLayout.BELOW, 0);//取消显示到root的下方
//        	 layoutParams3.bottomMargin = tv_left.getBottom()+dy/8;
//        	 tv_left.setLayoutParams(layoutParams3);
//        	 tv_right.setLayoutParams(layoutParams3);
        	 
        	 if(dy>0){
        	 topSize+=0.35;
        	 leftSize+= 0.05;
        	 bottomSize+=0.015;
        	 }
        	 else{ 
        		 topSize -=0.35;
        		 leftSize-=0.05;
            	 bottomSize-=0.015;
        	 
        	 }
         
        	 if(topSize<20)topSize = 20;
        	 else if(topSize>100)topSize = 100;
        	 if(leftSize<15)leftSize = 15;
        	 else if(leftSize>70)leftSize = 70;
        	 if(leftSize<10)leftSize = 10;
        	 else if(leftSize>50)leftSize = 50;
        	 
        	 Log.i("result:", "topSize:"+topSize+"leftSize:"+leftSize+"bottomSize:"+bottomSize);
        	 tv_top.setTextSize(topSize);
        	 tv_left.setTextSize(leftSize);
        	 tv_right.setTextSize(leftSize);
        	 tv_bottom.setTextSize(bottomSize);
        	 
        	 }else {
        		 
        		 topSize= 20;
        		 leftSize=15;
            	 bottomSize=10;
            	 tv_top.setTextSize(topSize);
            	 tv_left.setTextSize(leftSize);
            	 tv_right.setTextSize(leftSize);
            	 tv_bottom.setTextSize(bottomSize);
            	 
        	 }
        	 lastY = (int) event.getRawY();
             break; 
         }
         return false;
         }
}
