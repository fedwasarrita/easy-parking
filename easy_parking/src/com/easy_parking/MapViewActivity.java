package com.easy_parking;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_map_view, menu);
        return true;
    }
}
