package com.model.DTO;

import java.io.Serializable;

public class Contrainte  implements Serializable{
	
	private String typeContrainte;
	private String libelle;
	
	public Contrainte(String typeContrainte,String libelle){
		this.libelle=libelle;
		this.typeContrainte=typeContrainte;
	}
	public Contrainte(){
		
	}
	
	
	public String getTypeContrainte() {
		return typeContrainte;
	}
	public void setTypeContrainte(String typeContrainte) {
		this.typeContrainte = typeContrainte;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
