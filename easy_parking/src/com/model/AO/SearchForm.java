package com.model.AO;

import java.io.Serializable;

public class SearchForm implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2769956641355807698L;
	
	private Double latitude;
	private Double longitude;
	private Integer perimetre;
	private TypePlace typePlace;
	private Boolean isHandicap;
	private Boolean isSecured;

	public SearchForm(){
		
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

	public Integer getPerimetre() {
		return perimetre;
	}

	public void setPerimetre(Integer perimetre) {
		this.perimetre = perimetre;
	}

	public TypePlace getTypePlace() {
		return typePlace;
	}

	public void setTypePlace(TypePlace typePlace) {
		this.typePlace = typePlace;
	}

	public Boolean getIsHandicapee() {
		return isHandicap;
	}

	public void setIsHandicapee(Boolean isHandicapee) {
		this.isHandicap = isHandicapee;
	}

	public Boolean getIsSecurisee() {
		return isSecured;
	}

	public void setIsSecurisee(Boolean isSecurisee) {
		this.isSecured = isSecurisee;
	}

		
}
