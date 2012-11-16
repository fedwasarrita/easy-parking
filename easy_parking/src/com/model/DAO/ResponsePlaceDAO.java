package com.model.DAO;


public class ResponsePlaceDAO {

	private StatutDAO statut;
    private PlaceFullDAO place;
    
    public ResponsePlaceDAO() {
		// no args
	}
    
	public StatutDAO getStatut() {
		return statut;
	}
	public void setStatut(StatutDAO statut) {
		this.statut = statut;
	}

	public PlaceFullDAO getPlace() {
		return place;
	}

	public void setPlace(PlaceFullDAO place) {
		this.place = place;
	}

}
