package com.easy_parking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.model.AO.SearchForm;
import com.model.AO.TypePlace;

public class HomeActivity extends Activity {
	
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int seekBarStepSize = Integer.parseInt(getString(R.string.home_perimetre_step));
    	final int seekBarMinValue = Integer.parseInt(getString(R.string.home_perimetre_min));
        setContentView(R.layout.activity_home);
        SeekBar s = (SeekBar) findViewById(R.id.seekBarPerimetre);
        ((TextView)findViewById(R.id.TextViewPerimetreValue)).setText("" + s.getProgress() +"m");
        
        //controle pour la checkbox gratuite
        ((CheckBox)findViewById(R.id.checkBoxFree)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(!((CheckBox)findViewById(R.id.checkBoxNoFree)).isChecked() && !isChecked)
				{
					((CheckBox)findViewById(R.id.checkBoxFree)).setChecked(true);
				}
			}
		});

        // controle pour la checkbox payante
		((CheckBox)findViewById(R.id.checkBoxNoFree)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(!((CheckBox)findViewById(R.id.checkBoxFree)).isChecked() && !isChecked)
				{
					((CheckBox)findViewById(R.id.checkBoxNoFree)).setChecked(true);
				}
			}
		});
        
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
    	//Fill out the searching form
    	SearchForm search= new SearchForm();
    	if(((CheckBox)findViewById(R.id.checkBoxFree)).isChecked() && ((CheckBox)findViewById(R.id.checkBoxNoFree)).isChecked())
    	{
    		search.setTypePlace(TypePlace.ALL);
    	}
    	else if(((CheckBox)findViewById(R.id.checkBoxFree)).isChecked())
    	{
    		search.setTypePlace(TypePlace.FREE);
    	}
    	else
    	{
    		search.setTypePlace(TypePlace.PAYING);
    	}
    	
    	 CheckBox checkBox=(CheckBox)findViewById(R.id.checkBoxHandicap);
    	 search.setIsHandicapee(checkBox.isChecked());
    	 
    	 checkBox=(CheckBox)findViewById(R.id.checkBoxSecure);
    	 search.setIsSecurisee(checkBox.isChecked());
    	 
    	 SeekBar perimetreBar=(SeekBar) findViewById(R.id.seekBarPerimetre);
    	 search.setPerimetre(perimetreBar.getProgress());
    	 
    	 intent.putExtra("SearchForm",search);
    	
    	 if(view.getId() == R.id.buttonByGeolocalisation)
    	{

    		intent.putExtra("LocationWay", "ByGeolocalisation");
    		
    		
    		if(!checkDeviceConnectivity()){
    			showConnectionStatus(intent);
    		}
    		
             
    		//HomeActivity.this.startActivity(intent);
    	}
    	else
    		{
    		Editable adresse = ((EditText) findViewById(R.id.editTextAdress)).getText();
    		if(adresse != null && adresse.length() > 5)
    		{
	    		intent.putExtra("LocationWay", adresse.toString());
	    		if(!checkDeviceConnectivity()){
	    			showConnectionStatus(intent);
	    		}
	    		
    		}
    	}
    }
    /*
	 * Check Internet connection of the device. Only Mobile broadband and wifi
	 * connection are tested. The WiMax and bluetooth are not checked
	 */
	private boolean checkDeviceConnectivity() {
		ConnectivityManager cm = (ConnectivityManager) ((Context) HomeActivity.this).getSystemService(Context.CONNECTIVITY_SERVICE);
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
	private void showConnectionStatus(final Intent nextActivity){
		AlertDialog.Builder builder= new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle("Network status");
		builder.setMessage("You are not connected to internet");
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				//HomeActivity.this.startActivity(nextActivity);
				
				
			}
		});
		
		builder.setNegativeButton("Go to network settings", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Open android network settings
				Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
				//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				
			}
		});
		//Create and show the AlertDialog
		AlertDialog alert=builder.create();
		alert.show();	
	}
	

}
