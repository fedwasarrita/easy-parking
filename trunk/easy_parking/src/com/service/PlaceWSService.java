package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.model.AO.EasyException;
import com.model.AO.SearchForm;
import com.model.DAO.ResponseListPlaceDAO;
import com.model.DTO.Place;
import com.network.MyHttpClient;
import com.utils.Paths;

public class PlaceWSService{

	public static PlaceWSService instance;
	
	private MyHttpClient client;
	
	private PlaceWSService(){
		this.client = MyHttpClient.getInstance();
	}
	
	public static PlaceWSService getInstance(){
		if (instance == null){
			instance = new PlaceWSService();
		}
		return instance;
	}
	

	public ResponseListPlaceDAO getListPlace(SearchForm sForm) throws EasyException{
		ResponseListPlaceDAO response = null;
		try{
			//initialisation des paramètres à envoyé
			List<NameValuePair> postParameters = new ArrayList<NameValuePair>();	
			
			postParameters.add(new BasicNameValuePair("method","getListPlace"));
			postParameters.add(new BasicNameValuePair("lat",sForm.getLatitude().toString()));
			postParameters.add(new BasicNameValuePair("long",sForm.getLongitude().toString()));
			postParameters.add(new BasicNameValuePair("peri",sForm.getPerimetre().toString()));
			postParameters.add(new BasicNameValuePair("typePlace",sForm.getTypePlace().getValue()));
			postParameters.add(new BasicNameValuePair("isHandicap",sForm.getIsHandicapee().toString()));
			postParameters.add(new BasicNameValuePair("isSecured",sForm.getIsSecurisee().toString()));
			
			String json = this.client.post(Paths.ROOT_URL, postParameters);
			
			Gson vJsonObj = new Gson();
			response = vJsonObj.fromJson(json, ResponseListPlaceDAO.class);
			
			//tester response et statut
			//TODO : gérer les messages d'erreurs internationalisés
			if(response == null)
			{
				throw new EasyException("Echec lors du chargement des données");
			}
			if(!response.getStatut().getIsSuccess())
			{
				throw new EasyException("Echec lors du chargement des données");
			}
		}
		catch(Exception e)
		{
			//TODO : gérer les messages d'erreurs internationalisés
			throw new EasyException("Echec lors du chargement des données");
	}
		return response;
	}

	public Place getPlace(){
		try {
			URL urlWS = new URL(Paths.ROOT_URL);
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
