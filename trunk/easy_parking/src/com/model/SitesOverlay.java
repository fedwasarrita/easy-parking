package com.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class SitesOverlay extends ItemizedOverlay {
	private List items=new ArrayList();
	private Drawable marker=null;

	public SitesOverlay(Drawable marker) {
	super(marker);
	this.marker=marker;
	populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
	return (OverlayItem) (items.get(i));
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
	super.draw(canvas, mapView, shadow);
	
	boundCenterBottom(marker);
	}
	
	public void addNewOverlay(OverlayItem o){
		items.add(o);
		populate();
	}
	
	@Override
	protected boolean onTap(int i) {

	return(true);
	}
	
	@Override
	public int size() {
	return(items.size());
	}
}
