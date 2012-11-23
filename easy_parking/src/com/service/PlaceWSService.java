package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.model.AO.EasyException;
import com.model.AO.SearchForm;
import com.model.DAO.ResponseListPlaceDAO;
import com.model.DAO.ResponsePlaceDAO;
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
			postParameters.add(new BasicNameValuePair("lat",Double.toString(sForm.getLatitude()/1000000)));
			postParameters.add(new BasicNameValuePair("long",Double.toString(sForm.getLongitude()/1000000)));
			postParameters.add(new BasicNameValuePair("peri",sForm.getPerimetre().toString()));
			postParameters.add(new BasicNameValuePair("typePlace",sForm.getTypePlace().getValue()));
			postParameters.add(new BasicNameValuePair("isHandicap",sForm.getIsHandicapee() ? "1" : "0" ));
			postParameters.add(new BasicNameValuePair("isSecured",sForm.getIsSecurisee() ? "1" : "0"));
			
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

	public ResponsePlaceDAO getPlace(Integer id) throws EasyException{
		
		ResponsePlaceDAO response = null;
		try {
			
			//initialisation des paramètres à envoyé
			List<NameValuePair> postParameters = new ArrayList<NameValuePair>();	
			
			postParameters.add(new BasicNameValuePair("method","getPlace"));
			postParameters.add(new BasicNameValuePair("id",id.toString()));
			
			String json = this.client.post(Paths.ROOT_URL, postParameters);
			
			Gson vJsonObj = new Gson();
			response = vJsonObj.fromJson(json, ResponsePlaceDAO.class);
			
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
			
		} catch (Exception e) {
			//TODO : gérer les messages d'erreurs internationalisés
			throw new EasyException("Echec lors du chargement des données");
		}
		return response;
	}
}
