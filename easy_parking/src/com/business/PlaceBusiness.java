package com.business;

import java.util.ArrayList;
import java.util.List;

import com.model.AO.SearchForm;
import com.model.DTO.Place;
import com.provider.PlaceProvider;

public class PlaceBusiness {

	@SuppressWarnings("finally")
	public List<Place> GetPlacesByPosition(SearchForm form){
			List<Place> list = new ArrayList<Place>();
			
			try 
			{
				list = PlaceProvider.getInstance().GetAllPlaces(form);

			} 
			catch (Exception e) 
			{
				
			}
			finally
			{
				return list;
			}
	}
}
