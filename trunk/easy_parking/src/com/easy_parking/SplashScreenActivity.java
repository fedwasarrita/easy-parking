package com.easy_parking;


import java.util.Timer;
import java.util.TimerTask;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;


public class SplashScreenActivity extends Activity {

	// TimeOut in milliseconds
	private final int splashDelay = 5*1000;
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
		
		if(!checkDeviceConnectivity()){
			//task.cancel();
			//this.timeOutTimer.cancel();
			showConnectionStatus();
		}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_splash_screen, menu);
		return true;
	}

	/*
	 * Check Internet connection of the device. Only Mobile broadband and wifi
	 * connection are tested. The WiMax and bluetooth are not checked
	 */
	private boolean checkDeviceConnectivity() {
		ConnectivityManager cm = (ConnectivityManager) ((Context) SplashScreenActivity.this).getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if(activeNetwork==null)
			return false;
		if (!activeNetwork.isConnected()) {
			return false;
		} else {
			return true;
		}
	}
	
	/* Build the Network status pop-up */
	private void showConnectionStatus(){
		AlertDialog.Builder builder= new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle("Network status");
		builder.setMessage("You are not connected to internet");
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		
		builder.setNegativeButton("Go to network settings", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Open android network settings
				Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
				//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//intent.setClassName("com.android.settings", "com.android.phone.NetworkSetting");
				startActivity(intent);
				
			}
		});
		//Create and show the AlertDialog
		AlertDialog alert=builder.create();
		alert.show();	
	}
	
}
