package com.easy_parking;


import java.util.Locale;

import com.business.PlaceBusiness;
import com.google.android.maps.GeoPoint;
import com.model.data.DataProvider;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class HomeActivity extends Activity {
	
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int seekBarStepSize = Integer.parseInt(getString(R.string.home_perimetre_step));
    	final int seekBarMinValue = Integer.parseInt(getString(R.string.home_perimetre_min));
        setContentView(R.layout.activity_home);
        SeekBar s = (SeekBar) findViewById(R.id.seekBarPerimetre);
        ((TextView)findViewById(R.id.TextViewPerimetreValue)).setText("" + s.getProgress() +"m");
        s.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if(progress< seekBarMinValue)
				{
					progress = seekBarMinValue;
				}
				progress= ((int)Math.round(progress/seekBarStepSize))*seekBarStepSize;
				seekBar.setProgress(progress);
				((TextView)findViewById(R.id.TextViewPerimetreValue)).setText("" + progress +"m");
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
    
    public void showAdressBlock(View view) {
        ((LinearLayout) findViewById(R.id.AdressBlock)).setVisibility(0);
    }
    
    
    public void goToMapView(View view) {
    	Intent intent = new Intent(HomeActivity.this , TabResultsActivity.class);
    	if(view.getId() == R.id.buttonByGeolocalisation)
    	{
    		intent.putExtra("LocationWay", "ByGeolocalisation");
    		HomeActivity.this.startActivity(intent);
    	}
    	else
    	{
    		Editable adresse = ((EditText) findViewById(R.id.editTextAdress)).getText();
    		if(adresse != null && adresse.length() > 5)
    		{
	    		intent.putExtra("LocationWay", adresse.toString());
	    		HomeActivity.this.startActivity(intent);
    		}
    	}
    }
}
