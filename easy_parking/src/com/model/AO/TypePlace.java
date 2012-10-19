package com.model.AO;

public enum TypePlace {

	FREE("0"),
	PAYING("1"),
	ALL("2");
	
	protected String value;

	/** Constructeur */
	TypePlace(String pValue) {
		this.value = pValue;
	}

	public String getValue() {
	   return this.value;
	}
}
