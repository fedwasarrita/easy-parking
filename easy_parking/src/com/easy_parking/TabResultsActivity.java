package com.easy_parking;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.model.AO.SearchForm;

public class TabResultsActivity extends TabActivity {

	private TabHost tabHost;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_results);
        
        this.tabHost = getTabHost();
        
        // Tab for MapView
        TabSpec mapSpec = tabHost.newTabSpec("Carte");
        
        // setting Title and Icon for the Tab
        Intent tempIntent=this.getIntent();
        SearchForm searchF= (SearchForm)tempIntent.getSerializableExtra("SearchForm");
        
        mapSpec.setIndicator("Carte", getResources().getDrawable(R.drawable.ic_action_search_light));
        
        Intent mapIntent = new Intent(this, MapViewActivity.class);
        mapIntent.putExtra("LocationWay", getIntent().getStringExtra("LocationWay"));
        mapIntent.putExtra("SearchForm", searchF);
        mapSpec.setContent(mapIntent);
 
        // Tab for the List
        TabSpec listSpec = tabHost.newTabSpec("Songs");
        // setting Title and Icon for the Tab
        listSpec.setIndicator("Liste", getResources().getDrawable(R.drawable.ic_view_as_list_dark));
        Intent listIntent = new Intent(this, ListResultsActivity.class);
        listIntent.putExtra("LocationWay", getIntent().getStringExtra("LocationWay"));
        listIntent.putExtra("SearchForm", searchF);
        listSpec.setContent(listIntent);
 
        // Adding all TabSpec to TabHost
        tabHost.addTab(mapSpec); // Adding map tab
        tabHost.addTab(listSpec); // Adding list tab
        
        //Add listeners on tab buttons
       
        
        
        this.tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				if(tabId.equals("Carte")){
					getTabWidget().getChildTabViewAt(0).getResources().getDrawable(R.drawable.ic_action_search_light);
					getTabWidget().getChildTabViewAt(1).getResources().getDrawable(R.drawable.ic_view_as_list_dark);
					
				}else{
					getTabWidget().getChildTabViewAt(0).getResources().getDrawable(R.drawable.ic_action_search_dark);
					getTabWidget().getChildTabViewAt(1).getResources().getDrawable(R.drawable.ic_view_as_list_light);
				}
			}
			
		});
        
        
        
        
        
        
     /*   tabHost.getTabWidget().getChildAt(0).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getTabWidget().getChildTabViewAt(0).getResources().getDrawable(R.drawable.ic_action_search_light);
				getTabWidget().getChildTabViewAt(1).getResources().getDrawable(R.drawable.ic_view_as_list_dark);
				
			}
		});
        
        tabHost.getTabWidget().getChildAt(1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getTabWidget().getChildTabViewAt(0).getResources().getDrawable(R.drawable.ic_action_search_dark);
				getTabWidget().getChildTabViewAt(1).getResources().getDrawable(R.drawable.ic_view_as_list_light);
				
			}
		});
		
      */  
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tab_results, menu);
        return true;
    }
}
