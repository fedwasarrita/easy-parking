package com.easy_parking;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;


public class SplashScreenActivity extends Activity {

	// TimeOut in milliseconds
	private final int splashDelay = 3*1000;
	private Timer timeOutTimer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		
		
		
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				
				Intent homeIntent = new Intent().setClass(
						SplashScreenActivity.this, HomeActivity.class);
				

				startActivity(homeIntent);
				finish();
				
			}
		};
		this.timeOutTimer = new Timer();
		timeOutTimer.schedule(task, splashDelay);
		
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_splash_screen, menu);
		return true;
	}

	
}
