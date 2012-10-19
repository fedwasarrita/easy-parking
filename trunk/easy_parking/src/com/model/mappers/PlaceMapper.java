package com.model.mappers;

import com.model.DAO.PlaceDAO;
import com.model.DTO.CoordonneesGPS;
import com.model.DTO.Place;

public class PlaceMapper {
	
	public static Place MapPlaceDAOToDTO(PlaceDAO place)
	{
		Place vPlace = new Place();
		CoordonneesGPS coord = new CoordonneesGPS(place.getLatitude(), place.getLongitude());
		vPlace.setCoordonneesGPS(coord);
		vPlace.setGratuite(place.getIsFree());
		vPlace.setLibre(true);
		vPlace.setHandicapee(place.getIsHandicap());
		vPlace.setSecurisee(place.getIsSecured());
		return vPlace;
	}

}
