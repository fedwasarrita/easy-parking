package com.model.DAO;

public class PlaceDAO {

	public double latitude;
    public double longitude;
    public Boolean isFree;
    public Boolean isHandicap;
    public Boolean isSecured;
    
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Boolean getIsFree() {
		return isFree;
	}
	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}
	public Boolean getIsHandicap() {
		return isHandicap;
	}
	public void setIsHandicap(Boolean isHandicap) {
		this.isHandicap = isHandicap;
	}
	public Boolean getIsSecured() {
		return isSecured;
	}
	public void setIsSecured(Boolean isSecured) {
		this.isSecured = isSecured;
	}
}
