package com.easy_parking;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.model.DTO.Adresse;
import com.model.DTO.Place;
import com.model.adapters.PlaceAdapter;
import com.provider.PlaceProvider;

public class ListResultsActivity extends ListActivity {
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_results);
        if(PlaceProvider.listPlaces != null)
        {
        	setListAdapter(new PlaceAdapter(this, R.layout.row, (ArrayList<Place>) PlaceProvider.listPlaces));
        }
        
        
        ListView list = this.getListView();
        list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ListResultsActivity.this , PlaceDetailsActivity.class);
				intent.putExtra("Place",PlaceProvider.listPlaces.get(arg2));
				ListResultsActivity.this.startActivity(intent);
			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_results, menu);
        return true;
    }
}
