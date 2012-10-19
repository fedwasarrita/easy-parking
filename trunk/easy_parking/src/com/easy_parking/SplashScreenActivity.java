package com.easy_parking;

import java.util.Timer;
import java.util.TimerTask;

import com.model.AO.SearchForm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.SlidingDrawer;

public class SplashScreenActivity extends Activity {
	
	//TimeOut in milli seconds
	private final long delay=5*1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        
        
        TimerTask task= new TimerTask() {
        	
			
			@Override
			public void run() {
				finish();
				Intent homeIntent=new Intent().setClass(SplashScreenActivity.this, HomeActivity.class);
				startActivity(homeIntent);		
				
			}
		};
		Timer timer= new Timer();
		timer.schedule(task, delay);
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_splash_screen, menu);
        return true;
    }
}
