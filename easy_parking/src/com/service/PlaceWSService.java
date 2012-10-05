package com.service;

import java.util.ArrayList;

import com.model.DTO.Place;

public class PlaceWSService {

	public static PlaceWSService instance;
	
	private PlaceWSService(){
	}
	
	public static PlaceWSService getInstance(){
		if (instance == null){
			instance = new PlaceWSService();
		}
		return instance;
	}
	
	public ArrayList<Place> getListPlace(){
		return null;
	}

	public Place getPlace(){
		return null;
	}
}
