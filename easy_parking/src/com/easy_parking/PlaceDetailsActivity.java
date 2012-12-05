package com.easy_parking;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import com.model.DTO.Contrainte;
import com.model.DTO.Place;
import com.model.adapters.ContrainteUnitaireAdapter;

public class PlaceDetailsActivity extends Activity {

	private Place CurrentPlace= new Place();
	private final ArrayList<Contrainte> l_contraintes = new ArrayList<Contrainte>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        
        Intent currentIntent = getIntent();
        CurrentPlace = (Place) currentIntent.getSerializableExtra("Place");
        
//        //TODO appel couche m�tier -> service pour avoir les informations de la place
//        CurrentPlace.setAdresse(new Adresse("Boulevard Vauban","Lille","59000"));
//        CurrentPlace.setCoordonneesGPS(new CoordonneesGPS(50.629213, 3.0432389000000057));
//        CurrentPlace.setDistance(525);
//        CurrentPlace.setGratuite(true);
//        ArrayList<Contrainte> lContraintes = new ArrayList<Contrainte>();
//        lContraintes.add(new Contrainte("Horaire","D�pose minute"));
//        lContraintes.add(new Contrainte("R�servation","R�serv�e Livraisons"));
//        lContraintes.add(new Contrainte("R�servation","R�serv�e Riverains"));
//        lContraintes.add(new Contrainte("R�servation","R�serv�e Livraisons"));
//        lContraintes.add(new Contrainte("R�servation","R�serv�e Riverains"));
//        CurrentPlace.setContraintes(lContraintes);
//        CurrentPlace.setHandicapee(true);
//        CurrentPlace.setIdPlace(1);
//        CurrentPlace.setLibre(true);
//        CurrentPlace.setSecurisee(true);
//        CurrentPlace.setTarif("Gratuit");
//        CurrentPlace.setTypePlace("Classique");
//        
        UpdateUI();
    }
    
    public void UpdateUI(){
    	//Remplit l'adresse
    	TextView txAdresse;
    	txAdresse = (TextView)findViewById(R.id.TextAdressvalue);
    	txAdresse.setText(CurrentPlace.getAdresse().getAdresse());
    	
    	//Remplit le tarif
    	TextView txTarif;
    	txTarif = (TextView)findViewById(R.id.textViewtarif);
    	txTarif.setText(CurrentPlace.getTarif());
    	
    	//Remplit la donn�e concernant le handicap
    	TextView txHandicap;
    	txHandicap = (TextView)findViewById(R.id.textViewaccessibilite);
    	if(CurrentPlace.isHandicapee()){
    		txHandicap.setText(R.string.detail_info_accessH);
    	}
    	
    	//Remplit la donn�e sur la s�curisation de la place
    	TextView txSecurite;
    	txSecurite = (TextView)findViewById(R.id.textViewSecurisation);
    	if(CurrentPlace.isSecurisee()){
    		txSecurite.setText(R.string.detail_info_securisee);
    	}
    	
    	//Remplit le type de place
    	TextView txTypePlace;
    	txTypePlace = (TextView)findViewById(R.id.TextViewTypeValue);
    	txTypePlace.setText(CurrentPlace.getTypePlace());
    	
    	
    	//Remplit les contraintes
    	Iterator<Contrainte> ite = CurrentPlace.getContraintes().iterator();
    	while(ite.hasNext()){
    		Contrainte vContrainte = ite.next();
    		if(vContrainte.getTypeContrainte().equals("Horaire")){
    			//On met la contrainte horaire dans un emplacement sp�cifique
    			TextView txContrainteHoraire;
    			txContrainteHoraire = (TextView)findViewById(R.id.txView_contrainte_horaire);
    			txContrainteHoraire.setText(vContrainte.getLibelle());
    		}
    		else{
    			l_contraintes.add(vContrainte);
    		}
    		
    	}
    	ListView lViewContraintes;
		lViewContraintes = (ListView)findViewById(R.id.listViewContraintes);
		lViewContraintes.setAdapter(new ContrainteUnitaireAdapter(this, R.layout.contrainte_unitaire, l_contraintes));
    	
    	
    	
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_place_details, menu);
        return true;
    }
    
    
    
}
