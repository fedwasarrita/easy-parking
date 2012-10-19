package com.provider;

import java.util.ArrayList;
import java.util.List;

import com.model.AO.EasyException;
import com.model.AO.SearchForm;
import com.model.DAO.PlaceDAO;
import com.model.DAO.ResponseListPlaceDAO;
import com.model.DTO.Place;
import com.model.mappers.PlaceMapper;
import com.service.PlaceWSService;

public class PlaceProvider {

	public static List<Place> GetAllPlaces(SearchForm sForm) throws EasyException{
		
		List<Place> vListPlace = new ArrayList<Place>();
		ResponseListPlaceDAO vResponse = null;
		try
		{
			vResponse = PlaceWSService.getInstance().getListPlace(sForm);
		}
		catch(Exception e)
		{
			throw new EasyException(e.getMessage());
		}
		
		//mapper les place
		List<PlaceDAO> vListPlaceDao = vResponse.getPlaces();
		for(PlaceDAO vPlaceDao : vListPlaceDao)
		{
			vListPlace.add(PlaceMapper.MapPlaceDAOToDTO(vPlaceDao));
		}
		return vListPlace;
	}
}
