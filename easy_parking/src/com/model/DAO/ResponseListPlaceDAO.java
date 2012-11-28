package com.model.DAO;

import java.util.List;

public class ResponseListPlaceDAO {
	
	private StatutDAO statut;
    private List<PlaceFullDAO> places;
    
    public ResponseListPlaceDAO() {
		// no args
	}
    
	public StatutDAO getStatut() {
		return statut;
	}
	public void setStatut(StatutDAO statut) {
		this.statut = statut;
	}
	public List<PlaceFullDAO> getPlaces() {
		return places;
	}
	public void setPlaces(List<PlaceFullDAO> places) {
		this.places = places;
	}

}
