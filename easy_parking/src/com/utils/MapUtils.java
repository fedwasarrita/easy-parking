package com.utils;

import com.google.android.maps.GeoPoint;

public class MapUtils {
	
	private static  double rad(double x)
	{
		return x*Math.PI/180;
	}


	
	public static Double getDistance(GeoPoint p1, GeoPoint p2){
		  double R = 6371; // earth's mean radius in km
		  double dLat  = rad(p2.getLatitudeE6() - p1.getLatitudeE6());
		  double dLong = rad(p2.getLongitudeE6() - p1.getLongitudeE6());

		  double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(rad(p1.getLatitudeE6()) * Math.cos(rad(p2.getLatitudeE6()) * Math.sin(dLong/2) * Math.sin(dLong/2)));
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		  Double d = R * c * 1000;

		  return d;
	}
	
}
