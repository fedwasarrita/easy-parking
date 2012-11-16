package com.model.DAO;

public class PlaceDAO {

	private int idPlace;
	private double latitude;
    private double longitude;
    private Boolean isFree;
    private Boolean isHandicap;
    private Boolean isSecured;
    
    public PlaceDAO() {
		// no args
	}
    
	public int getIdPlace() {
		return idPlace;
	}

	public void setIdPlace(int idPlace) {
		this.idPlace = idPlace;
	}

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
