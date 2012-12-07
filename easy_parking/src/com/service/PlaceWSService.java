package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.text.Html;

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
	
	/**
	 * M�thode d'appel au web service afin de r�cup�rer une liste de place
	 * en fonction des informations saisies dans le formulaire
	 * @param sForm : le formulaire de recherche
	 * @return la reponse de la requ�te au Web Service
	 * @throws EasyException
	 */
	public ResponseListPlaceDAO getListPlace(SearchForm sForm) throws EasyException{
		ResponseListPlaceDAO response = null;
		try{
			//initialisation des param�tres � envoy�
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

			if(response == null)
			{
				throw new EasyException("Echec lors du chargement des donn�es");
			}
			if(!response.getStatut().getIsSuccess())
			{
				throw new EasyException("Echec lors du chargement des donn�es");
			}
		}
		catch(Exception e)
		{
			//TODO : g�rer les messages d'erreurs internationalis�s
			throw new EasyException("Echec lors du chargement des donn�es");
	}
		return response;
	}

	/**
	 * M�thode d'appel au web service afin de r�cup�rer une place
	 * en fonction de son id
	 * @param id : l'id de la place
	 * @return la r�ponse du Web Service
	 * @throws EasyException
	 */
	public ResponsePlaceDAO getPlace(Integer id) throws EasyException{
		
		ResponsePlaceDAO response = null;
		try {
			
			//initialisation des param�tres � envoy�
			List<NameValuePair> postParameters = new ArrayList<NameValuePair>();	
			
			postParameters.add(new BasicNameValuePair("method","getPlace"));
			postParameters.add(new BasicNameValuePair("id",id.toString()));
			
			String json = this.client.post(Paths.ROOT_URL, postParameters);
			
			Gson vJsonObj = new Gson();
			response = vJsonObj.fromJson(json, ResponsePlaceDAO.class);
			
			//tester response et statut
			//TODO : g�rer les messages d'erreurs internationalis�s
			if(response == null)
			{
				throw new EasyException("Echec lors du chargement des donn�es");
			}
			if(!response.getStatut().getIsSuccess())
			{
				throw new EasyException("Echec lors du chargement des donn�es");
			}
			
		} catch (Exception e) {
			//TODO : g�rer les messages d'erreurs internationalis�s
			throw new EasyException("Echec lors du chargement des donn�es");
		}
		return response;
	}
}
