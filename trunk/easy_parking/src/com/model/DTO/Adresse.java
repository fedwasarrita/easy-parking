package com.model.DTO;

import java.io.Serializable;

public class Adresse  implements Serializable{
	
	private String adresse;
	private String ville;
	private String codePostal;
	
	
	public Adresse(String rue,String ville,String codePostal){
		this.adresse=rue;
		this.ville=ville;
		this.codePostal=codePostal;
	}
	
	public Adresse(){
	
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
}
