package com.model.DAO;

public class PlaceDAO {

	private int idPlace;
	private double latitude;
    private double longitude;
    private String adresse;
	private String ville;
	private String codePostal;
    private int isFree;
    private int isHandicap;
    private int isSecured;
    
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
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public Boolean getIsFree() {
		return isFree == 0? false : true;
	}
	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}
	public Boolean getIsHandicap() {
		return isHandicap == 0? false : true;
	}
	public void setIsHandicap(int isHandicap) {
		this.isHandicap = isHandicap;
	}
	public Boolean getIsSecured() {
		return isSecured == 0? false : true;
	}
	public void setIsSecured(int isSecured) {
		this.isSecured = isSecured;
	}
}
