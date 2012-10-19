package com.provider;

import java.util.ArrayList;
import java.util.List;

import com.model.AO.EasyException;
import com.model.DAO.PlaceDAO;
import com.model.DAO.ResponseListPlaceDAO;
import com.model.DTO.CoordonneesGPS;
import com.model.DTO.Place;
import com.service.PlaceWSService;

public class PlaceProvider {

	public static List<Place> GetAllPlaces() throws EasyException{
		
		List<Place> vListPlace = new ArrayList<Place>();
		ResponseListPlaceDAO vResponse = null;
		try
		{
			vResponse = PlaceWSService.getInstance().getListPlace();
		}
		catch(Exception e)
		{
			throw new EasyException(e.getMessage());
		}
		
		//mapper les place
		List<PlaceDAO> vListPlaceDao = vResponse.getPlaces();
		for(PlaceDAO vPlaceDao : vListPlaceDao)
		{
			Place vPlace = new Place();
			CoordonneesGPS coord = new CoordonneesGPS(vPlaceDao.getLatitude(), vPlaceDao.getLongitude());
			vPlace.setCoordonneesGPS(coord);
			vPlace.setGratuite(vPlaceDao.getIsFree());
			vPlace.setLibre(true);
			vPlace.setHandicapee(vPlaceDao.getIsHandicap());
			vPlace.setSecurisee(vPlaceDao.getIsSecured());
			
			vListPlace.add(vPlace);
		}
		
		
		return vListPlace;
	}
}
