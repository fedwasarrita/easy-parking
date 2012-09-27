package com.easy_parking;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class MapViewActivity extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        final MapView mp = (MapView) findViewById(R.id.mapview);
        mp.setBuiltInZoomControls(true);
        MapController m = mp.getController();
        
        Geocoder geo = new Geocoder(this,Locale.getDefault());
        try 
        {
			List<Address> listAdress = geo.getFromLocationName("Rue du port, lille", 1);
			Address location = listAdress.get(0);
			m.setCenter(new GeoPoint((int)location.getLatitude(),(int)location.getLongitude()));
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //GEOLOCALISATION
        //
//        LocationManager lm = (LocationManager) getSystemService(this.LOCATION_SERVICE);
//        // Define a listener that responds to location updates
//        LocationListener locationListener = new LocationListener() {
//            public void onLocationChanged(Location location) {
//              // Called when a new location is found by the network location provider.
//              makeUseOfNewLocation(location);
//            }
//
//            private void makeUseOfNewLocation(Location location) {
//				// TODO Auto-generated method stub
//				MapController m = mp.getController();
//				
//				m.setCenter(new GeoPoint((int)location.getLatitude(),(int)location.getLongitude()));
//				
//			}
//
//			public void onStatusChanged(String provider, int status, Bundle extras) {}
//
//            public void onProviderEnabled(String provider) {}
//
//            public void onProviderDisabled(String provider) {}
//          };
//
//          // Register the listener with the Location Manager to receive location updates
//          lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_map_view, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
