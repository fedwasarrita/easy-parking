package com.model.AO;

import java.util.ArrayList;
import java.util.List;

public class EasyException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1932420513004404413L;
	
	private List<String> messageRessourceErreurs = new ArrayList<String>();

	public EasyException(){
		super();
	}
	
	public EasyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	public EasyException(String arg0) {
		super(arg0);
	}
	
	public EasyException(Throwable arg0) {
		super(arg0);
	}
	
	public EasyException(List<String> erreurs){
		this.messageRessourceErreurs = erreurs;
	}
	
	public List<String> getMessageRessource(){
		return this.messageRessourceErreurs;
	}
}
