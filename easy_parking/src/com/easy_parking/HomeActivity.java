package com.easy_parking;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class HomeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button btnByAdress = (Button) findViewById(R.id.buttonByAdress);
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
