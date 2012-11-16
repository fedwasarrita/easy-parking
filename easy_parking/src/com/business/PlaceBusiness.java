package com.business;

import java.io.IOException;
import java.util.ArrayList;
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
import com.model.DTO.Adresse;
import com.model.DTO.CoordonneesGPS;
import com.model.DTO.Place;
import com.provider.PlaceProvider;

import android.location.Location;
import android.os.Bundle;

public class PlaceBusiness {

	@SuppressWarnings("finally")
	public List<Place> GetPlacesByPosition(SearchForm form){
			List<Place> list = new ArrayList<Place>();
			
			try 
			{
				//list = PlaceProvider.getInstance().GetAllPlaces(form);
				Place p1 = new Place();
		        p1.setGratuite(false);
		        p1.setAdresse(new Adresse());
		        p1.getAdresse().setAdresse("31 rue de la Digue");
		        p1.setSecurisee(true);
		        p1.setDistance(99);
		        CoordonneesGPS c = new CoordonneesGPS();
		        c.setLatitude(50.631932);
		        c.setLongitude(3.046150);
		        p1.setCoordonneesGPS(c);
		        Place p2 = new Place();
		        p2.setGratuite(true);
		        p2.setAdresse(new Adresse());
		        p2.getAdresse().setAdresse("402 Boulevard Victore Hugo");
		        p2.setSecurisee(false);
		        p2.setHandicapee(true);
		        p2.setDistance(908);
		        CoordonneesGPS c2 = new CoordonneesGPS();
		        c2.setLatitude(50.633498);
		        c2.setLongitude(3.044380);
		        p2.setCoordonneesGPS(c2);
		        Place p3 = new Place();
		        p3.setGratuite(false);
		        p3.setAdresse(new Adresse());
		        p3.getAdresse().setAdresse("99 Boulevard Vauban");
		        p3.setSecurisee(false);
		        p3.setHandicapee(false);
		        p3.setDistance(190);
		        CoordonneesGPS c3 = new CoordonneesGPS();
		        c3.setLatitude(50.633099);
		        c3.setLongitude(3.042057);
		        p3.setCoordonneesGPS(c3);
		        Place p4 = new Place();
		        p4.setGratuite(true);
		        p4.setAdresse(new Adresse());
		        p4.getAdresse().setAdresse("73 Rue nationale");
		        p4.setSecurisee(false);
		        p4.setHandicapee(true);
		        p4.setDistance(45);
		        CoordonneesGPS c4 = new CoordonneesGPS();
		        c4.setLatitude(50.629213);
		        c4.setLongitude(3.0432389000000057);
		        p4.setCoordonneesGPS(c4);
		        list.add(p1);
		        list.add(p2);
		        list.add(p3);
		        list.add(p4);
			} 
			catch (Exception e) 
			{
				
			}
			finally
			{
				return list;
			}
	}
}
