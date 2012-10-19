package com.easy_parking;

import com.model.DTO.Adresse;
import com.model.DTO.Place;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class PlaceDetailsActivity extends Activity {

	private final Place CurrentPlace= new Place();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        
        //TODO appel couche métier -> service pour avoir les informations de la place
        CurrentPlace.setAdresse(new Adresse());
        UpdateUI();
    }
    
    public void UpdateUI(){
    	TextView tx;
    	tx = (TextView)findViewById(R.id.TextAdressvalue);
    	tx.setText(CurrentPlace.getAdresse().getAdresse());
    	tx = (TextView)findViewById(R.id.TextAdressvalue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_place_details, menu);
        return true;
    }
    
    
    
}
