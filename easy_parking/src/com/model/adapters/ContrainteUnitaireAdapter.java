package com.model.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.easy_parking.R;
import com.model.DTO.Contrainte;

public class ContrainteUnitaireAdapter extends ArrayAdapter<Contrainte> {

	private ArrayList<Contrainte> items;

	public ContrainteUnitaireAdapter(Context context, int textViewResourceId,
			ArrayList<Contrainte> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = ((Activity) this.getContext())
					.getLayoutInflater();
			v = vi.inflate(R.layout.contrainte_unitaire, null);
		}
		Contrainte contrainteU = items.get(position);
		if (contrainteU != null) {
			//On replace le type de contrainte
			TextView txViewTypeContrainte = (TextView) v.findViewById(R.id.TypeContrainteUnitaire);
			txViewTypeContrainte.setText(contrainteU.getTypeContrainte());
			
			//De même avec le libéllé de la contrainte
			TextView txViewLibelleContrainte = (TextView) v.findViewById(R.id.LibelleContrainteUnitaire);
			txViewLibelleContrainte.setText(contrainteU.getLibelle());
			
		}
		return v;
	}

}
