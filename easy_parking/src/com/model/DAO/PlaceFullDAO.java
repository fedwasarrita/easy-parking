package com.model.DAO;

import java.util.List;

public class PlaceFullDAO {

	private double latitude;
	private double longitude;
	private boolean isFree;
	private boolean isHandicap;
	private boolean isSecured;
	private String adresse;
	private String ville;
	private String codePostal;
	private List<ContrainteDAO> contraintes;
	private String typePlace;
	private String tarif;
	
	public PlaceFullDAO() {
		// no args
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

	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	public boolean isHandicap() {
		return isHandicap;
	}
	public void setHandicap(boolean isHandicap) {
		this.isHandicap = isHandicap;
	}
	public boolean isSecured() {
		return isSecured;
	}
	public void setSecured(boolean isSecured) {
		this.isSecured = isSecured;
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
	public List<ContrainteDAO> getContraintes() {
		return contraintes;
	}
	public void setContraintes(List<ContrainteDAO> contraintes) {
		this.contraintes = contraintes;
	}
	public String getTypePlace() {
		return typePlace;
	}
	public void setTypePlace(String typePlace) {
		this.typePlace = typePlace;
	}
	public String getTarif() {
		return tarif;
	}
	public void setTarif(String tarif) {
		this.tarif = tarif;
	}
}
