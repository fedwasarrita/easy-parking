package com.easy_parking;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;

import com.business.PlaceBusiness;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.model.AO.SearchForm;
import com.model.AO.SitesOverlay;
import com.model.DTO.Place;
import com.provider.PlaceProvider;

public class MapViewActivity extends MapActivity {

	MapView mapView;
	MapController mapController;
	SearchForm searchForm;
	PopupWindow pw;
	ProgressDialog vSpinner;

	Boolean isMarketsSet = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_view);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapController = mapView.getController();
		Intent currentIntent = getIntent();

		// Launch the Spinner while loading the data through web services
		this.vSpinner = ProgressDialog.show(this, "", "Chargement en cours...",
				false, true);

		if (currentIntent.getStringExtra("LocationWay").equals(
				"ByGeolocalisation")) {
			SetCursorByGeolocalisation();
		} else {
			SetCursorByAdresse(getIntent().getStringExtra("LocationWay"));
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_settings:
			ShowLegend();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void setMarkers(GeoPoint origine, List<Place> places) {
		List<Overlay> mapOverlays = mapView.getOverlays();
		OverlayItem o = new OverlayItem(origine, "Vous êtes ici", "");
		Drawable marker = getResources().getDrawable(R.drawable.ic_red_dot);
		SitesOverlay s = new SitesOverlay(marker, this);
		s.addOverlay(o);
		SitesOverlay s2 = new SitesOverlay(getResources().getDrawable(
				R.drawable.ic_blue_dot), this);
		if (places != null) {
			for (Place p : places) {
				float[] results = new float[20];
				Location.distanceBetween(origine.getLatitudeE6(), origine
						.getLongitudeE6(),
						p.getCoordonneesGPS().getLatitude() * 1000000, p
								.getCoordonneesGPS().getLongitude() * 1000000,
						results);
				p.setDistance((int) (results[0] / 100000));
				Double latitude = p.getCoordonneesGPS().getLatitude() * 1000000;
				Double longitude = p.getCoordonneesGPS().getLongitude() * 1000000;
				OverlayItem m = new OverlayItem(new GeoPoint(
						latitude.intValue(), longitude.intValue()), p
						.getAdresse().getAdresse(), "");
				s2.addOverlay(m);
			}
		}
		mapOverlays.add(s);
		mapOverlays.add(s2);

		// Data loaded, close the spinner
		this.vSpinner.dismiss();

		PlaceProvider.listPlaces = places;

	}

	public void SetCursorByGeolocalisation() {
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location
				// provider.
				makeUseOfNewLocation(location);
			}

			private void makeUseOfNewLocation(Location location) {
				if (!isMarketsSet) {
					Double latitude = location.getLatitude() * 1000000;
					Double longitude = location.getLongitude() * 1000000;
					GeoPoint origine = new GeoPoint(latitude.intValue(),
							longitude.intValue());
					mapController.setCenter(origine);

					// Add geo informations to SearchForm
					Intent currentIntent = getIntent();
					searchForm = (SearchForm) currentIntent
							.getSerializableExtra("SearchForm");
					searchForm.setLatitude(latitude);
					searchForm.setLongitude(longitude);
					searchAllPlace(origine);
					isMarketsSet = true;
				}

			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};

		// Register the listener with the Location Manager to receive location
		// updates
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
				locationListener);
	}

	public void SetCursorByAdresse(String adresse) {
		Geocoder geo = new Geocoder(this, Locale.getDefault());
		try {
			List<Address> listAdress = geo.getFromLocationName(adresse, 1);
			Address location = listAdress.get(0);
			Double latitude = location.getLatitude() * 1000000;
			Double longitude = location.getLongitude() * 1000000;
			GeoPoint origine = new GeoPoint(latitude.intValue(),
					longitude.intValue());
			mapController.setCenter(origine);
			mapController.setZoom(16);

			// Add geo informations to SearchForm
			Intent currentIntent = getIntent();
			searchForm = (SearchForm) currentIntent
					.getSerializableExtra("SearchForm");
			searchForm.setLatitude(latitude);
			searchForm.setLongitude(longitude);
			searchAllPlace(origine);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchAllPlace(GeoPoint origine) {
		try {
			PlaceBusiness b = new PlaceBusiness();
			List<Place> places = b.GetPlacesByPosition(searchForm);
			setMarkers(origine, places);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_map_view, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void ShowLegend() {
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		pw = new PopupWindow(inflater.inflate(R.layout.legend_popup, null,
				false), 250, 230, true);

		// The code below assumes that the root container has an id called
		// 'main'
		pw.showAtLocation(this.findViewById(R.id.mapview), Gravity.CENTER, 0, 0);

	}

	// M�thode de fermeture de la popup
	public void closePopup(View view) {
		pw.dismiss();

	}
}