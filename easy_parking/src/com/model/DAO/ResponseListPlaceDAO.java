package com.model.DAO;

import java.util.List;

public class ResponseListPlaceDAO {
	
	private StatutDAO statut;
    private List<PlaceDAO> places;
    
    public ResponseListPlaceDAO() {
		// no args
	}
    
	public StatutDAO getStatut() {
		return statut;
	}
	public void setStatut(StatutDAO statut) {
		this.statut = statut;
	}
	public List<PlaceDAO> getPlaces() {
		return places;
	}
	public void setPlaces(List<PlaceDAO> places) {
		this.places = places;
	}

}
