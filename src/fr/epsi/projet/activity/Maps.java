package fr.epsi.projet.activity;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.epsi.projet.R;
import fr.epsi.projet.beans.Emplacement;
import fr.epsi.projet.common.Constantes;
import fr.epsi.projet.service.ServiceRest;

public class Maps extends FragmentActivity /*implements OnMarkerClickListener*/{

	// Google Map
	private GoogleMap googleMap;
	private ServiceRest serviceRest = new ServiceRest();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);



		try {

			// Compatibility Thread -- pour les probl�mes d'appel � l'API REST
			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

			// Loading map
			initilizeMap();
			LatLng position = new LatLng(Constantes.LATITUDE_NANTES, Constantes.LONGITUDE_NANTES);
			//positionnement + zoom sur Nantes centre pour le moment
			CameraPosition cameraPosition = new CameraPosition.Builder().target(
					position).zoom(12).build();

			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); 

			List<Emplacement> all = serviceRest.getBennes();
			System.out.println("Nombre de point : " + all.size());
			MarkerOptions marker;
			for (Emplacement e : all) {
				position = new LatLng(e.get_l()[0], e.get_l()[1]);
				marker = new MarkerOptions().position(position).title(e.getAdresse() + " \n" + e.getCommune());
				marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_benne));
				googleMap.addMarker(marker);
			}


			googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

				public void onInfoWindowClick(Marker marker) {
					
					//TODO renvoyer vers l'activit� apr�s avoir recherch� le plus proche des coordon�es
					/*Intent i = new Intent(getActivity(), NewActivity.class);
            startActivity(i);
        	Emplacement e = serviceRest.getGeoBenne(marker.getPosition().latitude, marker.getPosition().longitude);
        	Toast.makeText(getApplicationContext(), e.get_l()[0]  + "-- " + e.get_l()[1], Toast.LENGTH_SHORT).show();
        	System.out.println(marker.getPosition().latitude + " -- " + marker.getPosition().longitude);
        	System.out.println(e.get_l()[0] + " -- " + e.get_l()[1]);
					 */


					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Maps.this);

					// set title
					alertDialogBuilder.setTitle("Se rendre � cette benne?");

					// set dialog message
					alertDialogBuilder
					.setMessage("Click yes to exit!")
					.setCancelable(true)
					.setPositiveButton("Oui",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
							Toast.makeText(getApplicationContext(), "Youhou on va � la benne.", Toast.LENGTH_LONG).show();
						}
					})
					.setNegativeButton("Non",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, just close
							// the dialog box and do nothing
							Toast.makeText(getApplicationContext(), "Oh non on reste l� :(", Toast.LENGTH_LONG).show();
							dialog.cancel();
						}
					});

					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();

					// show it
					alertDialog.show();
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}
}
