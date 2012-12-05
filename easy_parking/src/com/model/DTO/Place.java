package com.model.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Place implements Serializable{
	
	private int idPlace;
	private Adresse adresse;
	private CoordonneesGPS coordonneesGPS;
	private int distance;
	private boolean libre;
	private boolean gratuite;
	private boolean handicapee;
	private boolean securisee;
	private String typePlace;
	private List<Contrainte> contraintes;
	private String tarif;
	
	
	public Place(){
		this.contraintes=new ArrayList<Contrainte>();
	}
	
	
	public int getIdPlace() {
		return idPlace;
	}
	public void setIdPlace(int idPlace) {
		this.idPlace = idPlace;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public CoordonneesGPS getCoordonneesGPS() {
		return coordonneesGPS;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public void setCoordonneesGPS(CoordonneesGPS coordonneesGPS) {
		this.coordonneesGPS = coordonneesGPS;
	}
	public boolean isLibre() {
		return libre;
	}
	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	public boolean isGratuite() {
		return gratuite;
	}
	public void setGratuite(boolean gratuite) {
		this.gratuite = gratuite;
	}
	public boolean isHandicapee() {
		return handicapee;
	}
	public void setHandicapee(boolean handicapee) {
		this.handicapee = handicapee;
	}
	public boolean isSecurisee() {
		return securisee;
	}
	public void setSecurisee(boolean securisee) {
		this.securisee = securisee;
	}
	public String getTypePlace() {
		return typePlace;
	}
	public void setTypePlace(String typePlace) {
		this.typePlace = typePlace;
	}
	public List<Contrainte> getContraintes() {
		return contraintes;
	}
	public void setContraintes(List<Contrainte> contraintes) {
		this.contraintes = contraintes;
	}
	public String getTarif() {
		return tarif;
	}
	public void setTarif(String tarif) {
		this.tarif = tarif;
	}
}
