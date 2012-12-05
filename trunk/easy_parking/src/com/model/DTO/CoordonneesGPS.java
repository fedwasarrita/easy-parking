package com.model.DTO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CoordonneesGPS  implements Serializable{
	
	private Double latitude;
	private Double longitude;
	
	
	public CoordonneesGPS(Double latitude,Double longitude){
		this.latitude=latitude;
		this.longitude=longitude;
	}
	public CoordonneesGPS(){

	}
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
