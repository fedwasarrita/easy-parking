package com.model.mappers;

import java.util.ArrayList;
import java.util.List;

import com.model.DAO.ContrainteDAO;
import com.model.DAO.PlaceDAO;
import com.model.DAO.PlaceFullDAO;
import com.model.DTO.Adresse;
import com.model.DTO.Contrainte;
import com.model.DTO.CoordonneesGPS;
import com.model.DTO.Place;

public class PlaceMapper {
	
	public static Place MapPlaceDAOToDTO(PlaceDAO place)
	{
		Place vPlace = new Place();
		vPlace.setIdPlace(place.getIdPlace());
		CoordonneesGPS coord = new CoordonneesGPS(place.getLatitude(), place.getLongitude());
		vPlace.setCoordonneesGPS(coord);
		vPlace.setGratuite(place.getIsFree());
		vPlace.setLibre(true);
		vPlace.setHandicapee(place.getIsHandicap());
		vPlace.setSecurisee(place.getIsSecured());
		return vPlace;
	}
	
	public static Place MapPlaceDAOToDTO(PlaceFullDAO place)
	{
		Place vPlace = new Place();
		//Adresse
		Adresse adresse = new Adresse(place.getAdresse(), place.getVille(), place.getCodePostal());
		vPlace.setAdresse(adresse);
		
		//Liste des contraintes
		List<Contrainte> contraintes = new ArrayList<Contrainte>();
		for(ContrainteDAO contrainteDAO : place.getContraintes())
		{
			Contrainte vContrainte = new Contrainte();
			vContrainte.setLibelle(contrainteDAO.getDetailContrainte());
			vContrainte.setTypeContrainte(contrainteDAO.getTypeContrainte());
			
			contraintes.add(vContrainte);
		}
		vPlace.setContraintes(contraintes);
		
		CoordonneesGPS coords = new CoordonneesGPS(place.getLatitude(), place.getLongitude());
		vPlace.setCoordonneesGPS(coords);
		
		vPlace.setGratuite(place.isFree());
		vPlace.setHandicapee(place.isHandicap());
		vPlace.setLibre(true);
		vPlace.setSecurisee(place.isSecured());
		vPlace.setTarif(place.getTarif());
		
		
		return vPlace;
	}

}
