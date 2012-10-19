package com.easy_parking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
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
