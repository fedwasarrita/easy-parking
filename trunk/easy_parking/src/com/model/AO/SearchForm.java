package com.model.AO;

import java.io.Serializable;

public class SearchForm implements Serializable{
	
	
	private double latitude;
	private double longitude;
	private int perimetre;
	private boolean gratuite;
	private boolean payant;
	private boolean handicapee;
	private boolean securisee;

	
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


	public int getPerimetre() {
		return perimetre;
	}


	public void setPerimetre(int perimetre) {
		this.perimetre = perimetre;
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


	public SearchForm(){
		
	}


	public boolean isPayant() {
		return payant;
	}


	public void setPayant(boolean payant) {
		this.payant = payant;
	}
	
	
	
	
}
