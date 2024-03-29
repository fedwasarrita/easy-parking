package com.model.DAO;

import java.util.ArrayList;
import java.util.List;

public class PlaceFullDAO {

	private double latitude;
	private double longitude;
	private int isFree;
	private int isHandicap;
	private int isSecured;
	private String adresse;
	private String ville;
	private String codePostal;
	private String typePlace;
	private String tarif;
	private List<ContrainteDAO> contraintes = new ArrayList<ContrainteDAO>();

	
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

	public int isFree() {
		return isFree;
	}
	public void setFree(int isFree) {
		this.isFree = isFree;
	}
	public int isHandicap() {
		return isHandicap;
	}
	public void setHandicap(int isHandicap) {
		this.isHandicap = isHandicap;
	}
	public int isSecured() {
		return isSecured;
	}
	public void setSecured(int isSecured) {
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
