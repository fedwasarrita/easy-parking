package com.model.adapters;

import java.util.ArrayList;

import com.easy_parking.R;
import com.model.DTO.Place;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class PlaceAdapter extends ArrayAdapter<Place> {

	private ArrayList<Place> items;

    public PlaceAdapter(Context context, int textViewResourceId, ArrayList<Place> items) {
            super(context, textViewResourceId, items);
            this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) 
            {
                LayoutInflater vi = ((Activity)this.getContext()).getLayoutInflater();
                v = vi.inflate(R.layout.row, null);
            }
            Place place = items.get(position);
            if (place != null) 
            {
            	v.findViewById(R.id.IsPayante).setVisibility(place.isGratuite() ? 8 : 0);
            	v.findViewById(R.id.IsHandicapee).setVisibility(place.isHandicapee() ? 0 : 8);
            	v.findViewById(R.id.IsSecurisee).setVisibility(place.isSecurisee() ? 0 : 8);
            	TextView a = (TextView) v.findViewById(R.id.PlaceAdresse);
            	if(place.getAdresse() != null)
            	{
            		if(place.getAdresse().getAdresse()!= null)
            		{
                    	a.setText(place.getAdresse().getAdresse());
            		}
            	}
            	TextView b = (TextView) v.findViewById(R.id.PlaceDistance);
            	b.setText("A " + place.getDistance() + "m");
            }
            return v;
    }
}
