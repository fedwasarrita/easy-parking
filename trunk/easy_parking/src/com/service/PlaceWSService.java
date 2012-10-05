package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.content.res.Resources;

import com.easy_parking.R;
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
		try {
			URL urlWS = new URL("url");
			URLConnection connectionWS = urlWS.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connectionWS.getInputStream(), "UTF-8"));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
