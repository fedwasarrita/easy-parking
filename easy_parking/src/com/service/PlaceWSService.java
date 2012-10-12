package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.easy_parking.R;
import com.model.AO.EasyException;
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
	

	public ArrayList<Place> getListPlace() throws EasyException{
		
		try{
			//initialisation des paramètres à envoyé
			List<NameValuePair> postParameters = new ArrayList<NameValuePair>();	
			
			postParameters.add(new BasicNameValuePair("method","getListPlace"));
			postParameters.add(new BasicNameValuePair("lat","0"));
			postParameters.add(new BasicNameValuePair("long","0"));
			postParameters.add(new BasicNameValuePair("peri","10"));
			postParameters.add(new BasicNameValuePair("isFree","1"));
			postParameters.add(new BasicNameValuePair("isHandicap","1"));
			postParameters.add(new BasicNameValuePair("isSecured","1"));
			
			String json = this.client.post(Paths.ROOT_URL, postParameters);
			
			JSONObject vJsonObj = new JSONObject(json);
			
			
		}
		catch(Exception e)
		{
			throw new EasyException(getString(R.string.error_ws));
		}
		return null;
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
