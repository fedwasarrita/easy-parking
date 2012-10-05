package com.model.data;

import com.google.android.maps.GeoPoint;

public class DataProvider {
	
	private static GeoPoint origine;

	public static boolean available;
	
	public static GeoPoint getOrigine() {
		return origine;
	}
	public static void setOrigine(GeoPoint origine) {
		available = true;
		DataProvider.origine = origine;
	}
}
