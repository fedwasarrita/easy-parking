package com.easy_parking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.model.AO.SearchForm;
import com.model.AO.SitesOverlay;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;

public class MapViewActivity extends MapActivity {

	MapView mapView;
	MapController mapController;
	SearchForm searchForm;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapController = mapView.getController();
        Intent currentIntent = getIntent();
        if (currentIntent.getStringExtra("LocationWay").equals("ByGeolocalisation"))
        {
        	SetCursorByGeolocalisation();
        }
        else
        {
        	SetCursorByAdresse(getIntent().getStringExtra("LocationWay"));
        }
    }

    public void SetCursorByGeolocalisation(){
    	LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
              // Called when a new location is found by the network location provider.
              makeUseOfNewLocation(location);
            }

            private void makeUseOfNewLocation(Location location) {
				Double latitude = location.getLatitude() * 1000000;
				Double longitude  = location.getLongitude() * 1000000;
				GeoPoint origine = new GeoPoint(latitude.intValue(),longitude.intValue());
				mapController.setCenter(origine);
				OverlayItem o = new OverlayItem(origine,"Vous êtes ici","cool");
				Drawable marker=getResources().getDrawable(R.drawable.ic_blue_dot);
				SitesOverlay s = new SitesOverlay(marker);
				s.addNewOverlay(o);
				mapView.getOverlays().clear();
				mapView.getOverlays().add(s);
				
				//Add geo informations to SearchForm
				Intent currentIntent=getIntent();
				searchForm=(SearchForm)currentIntent.getSerializableExtra("SearchForm");
				searchForm.setLatitude(latitude);
				searchForm.setLongitude(longitude);
				currentIntent.putExtra("SearchForm", searchForm);
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
          };

          // Register the listener with the Location Manager to receive location updates
          lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }
    
    public void SetCursorByAdresse(String adresse){
        Geocoder geo = new Geocoder(this,Locale.getDefault());
        try 
        {
			List<Address> listAdress = geo.getFromLocationName(adresse, 1);
			Address location = listAdress.get(0);
			Double latitude = location.getLatitude() * 1000000;
			Double longitude  = location.getLongitude() * 1000000;
			GeoPoint origine = new GeoPoint(latitude.intValue(),longitude.intValue());
			mapController.setCenter(origine);
			mapController.setZoom(16);
			OverlayItem o = new OverlayItem(origine,"Vous ï¿½tes ici","cool");
			Drawable marker=getResources().getDrawable(R.drawable.ic_blue_dot);
			SitesOverlay s = new SitesOverlay(marker);
			s.addNewOverlay(o);
			mapView.getOverlays().clear();
			mapView.getOverlays().add(s);
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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