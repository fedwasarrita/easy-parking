package com.business;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;

import com.easy_parking.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import com.model.AO.SitesOverlay;
import android.location.Location;
import android.os.Bundle;

public class PlaceBusiness {

	public GeoPoint GetPositionByAdress(String adresse, Geocoder geo){
	        try 
	        {
				List<Address> listAdress = geo.getFromLocationName(adresse, 1);
				Address location = listAdress.get(0);
				Double latitude = location.getLatitude() * 1000000;
				Double longitude  = location.getLongitude() * 1000000;
				GeoPoint origine = new GeoPoint(latitude.intValue(),longitude.intValue());
				return origine;
//				mapController.setCenter(origine);
//				mapController.setZoom(16);
//				OverlayItem o = new OverlayItem(origine,"Vous �tes ici","cool");
//				Drawable marker=getResources().getDrawable(R.drawable.ic_blue_dot);
//				SitesOverlay s = new SitesOverlay(marker);
//				s.addNewOverlay(o);
//				mapView.getOverlays().clear();
//				mapView.getOverlays().add(s);
			} 
	        catch (IOException e) {
	        	//TODO
				return new GeoPoint(1,1);
			}
	}
}
