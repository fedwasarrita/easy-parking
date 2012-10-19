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
import com.model.AO.EasyException;
import com.model.AO.SearchForm;
import com.model.AO.SitesOverlay;
import com.model.DTO.Place;
import com.provider.PlaceProvider;

import android.location.Location;
import android.os.Bundle;

public class PlaceBusiness {

	@SuppressWarnings("finally")
	public List<Place> GetPlacesByPosition(SearchForm form){
			List<Place> list = null;
			try 
			{
				
				list= PlaceProvider.GetAllPlaces(form);
			} 
			catch (EasyException e) {
				
			}
			finally
			{
				return list;
			}
	}
}
