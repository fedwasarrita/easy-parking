package com.easy_parking;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabResultsActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_results);
        
        TabHost tabHost = getTabHost();
        
        // Tab for MapView
        TabSpec mapSpec = tabHost.newTabSpec("Carte");
        // setting Title and Icon for the Tab
        mapSpec.setIndicator("Carte", getResources().getDrawable(R.drawable.ic_action_search));
        Intent mapIntent = new Intent(this, MapViewActivity.class);
        mapSpec.setContent(mapIntent);
 
        // Tab for the List
        TabSpec listSpec = tabHost.newTabSpec("Songs");
        // setting Title and Icon for the Tab
        listSpec.setIndicator("Liste", getResources().getDrawable(R.drawable.ic_launcher));
        Intent listIntent = new Intent(this, ListResultsActivity.class);
        listSpec.setContent(listIntent);
 
        // Adding all TabSpec to TabHost
        tabHost.addTab(mapSpec); // Adding map tab
        tabHost.addTab(listSpec); // Adding list tab
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tab_results, menu);
        return true;
    }
}
