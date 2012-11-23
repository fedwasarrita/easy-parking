package com.provider;

import java.util.ArrayList;
import java.util.List;

import com.model.AO.EasyException;
import com.model.AO.SearchForm;
import com.model.DAO.PlaceDAO;
import com.model.DAO.ResponseListPlaceDAO;
import com.model.DAO.ResponsePlaceDAO;
import com.model.DTO.Place;
import com.model.mappers.PlaceMapper;
import com.service.PlaceWSService;

public class PlaceProvider {
	
	private static PlaceProvider instance;
	
	
	public static PlaceProvider getInstance(){
		if (instance == null){
			instance = new PlaceProvider();
		}
		return instance;
	}


	/**
	 * Méthode permettant de récupérer la liste des places via un webservice
	 * @param sForm : formulaire de recherche
	 * @return une liste de place
	 * @throws EasyException
	 */
	public List<Place> GetAllPlaces(SearchForm sForm) throws EasyException{
		
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
		
		//mapper les places
		List<PlaceDAO> vListPlaceDao = vResponse.getPlaces();
		for(PlaceDAO vPlaceDao : vListPlaceDao)
		{
			vListPlace.add(PlaceMapper.MapPlaceDAOToDTO(vPlaceDao));
		}
		return vListPlace;
	}
	
	/**
	 * Méthode permettant de récupérer une place via un webservice
	 * @param id : l'id de la place à récupérer
	 * @return une place
	 * @throws EasyException
	 */
	public static Place GetPlace(Integer id) throws EasyException{
		
		Place vPlace = new Place();
		ResponsePlaceDAO vResponse = null;
		
		try{
			vResponse = PlaceWSService.getInstance().getPlace(id);	
		}
		catch(Exception e)
		{
			throw new EasyException(e.getMessage());
		}
		
		vPlace = PlaceMapper.MapPlaceDAOToDTO(vResponse.getPlace());
		
		return vPlace;
	}
}
