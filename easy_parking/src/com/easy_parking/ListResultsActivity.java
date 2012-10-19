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

public class ListResultsActivity extends ListActivity {
	
	private final ArrayList<Place> m_places = new ArrayList<Place>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_results);
        Place p1 = new Place();
        p1.setGratuite(false);
        p1.setAdresse(new Adresse());
        p1.getAdresse().setAdresse("31 rue de la Digue");
        p1.setSecurisee(true);
        p1.setDistance(99);
        Place p2 = new Place();
        p2.setGratuite(true);
        p2.setAdresse(new Adresse());
        p2.getAdresse().setAdresse("402 Boulevard Victore Hugo");
        p2.setSecurisee(false);
        p2.setHandicapee(true);
        p2.setDistance(908);
        Place p3 = new Place();
        p3.setGratuite(false);
        p3.setAdresse(new Adresse());
        p3.getAdresse().setAdresse("99 Boulevard Vauban");
        p3.setSecurisee(false);
        p3.setHandicapee(false);
        p3.setDistance(190);
        Place p4 = new Place();
        p4.setGratuite(true);
        p4.setAdresse(new Adresse());
        p4.getAdresse().setAdresse("73 Rue nationale");
        p4.setSecurisee(false);
        p4.setHandicapee(true);
        p4.setDistance(45);
        m_places.add(p1);
        m_places.add(p2);
        m_places.add(p3);
        m_places.add(p4);
        setListAdapter(new PlaceAdapter(this, R.layout.row, m_places));
        
        ListView list = this.getListView();
        list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ListResultsActivity.this , PlaceDetailsActivity.class);
				intent.putExtra("Place",m_places.get(arg2));
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
