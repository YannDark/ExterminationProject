package fr.epsi.projet.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.epsi.projet.R;
import fr.epsi.projet.beans.Emplacement;
import fr.epsi.projet.common.Constantes;
import fr.epsi.projet.service.ServiceRest;

public class Map extends Activity {

	// Google Map
    private GoogleMap googleMap;
    private ServiceRest serviceRest = new ServiceRest();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        
 
        try {
        	
        	// Compatibility Thread -- pour les problèmes d'appel à l'API REST
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
            MarkerOptions marker;
            for (Emplacement e : all) {
            	position = new LatLng(e.get_l()[0], e.get_l()[1]);
            	marker = new MarkerOptions().position(position).title("Hello Maps ");
            	marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_benne));
            	googleMap.addMarker(marker);
			}
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
    
    public void displayBottleBank() {
    	//TODO getting the different bottle banks locations from distant DB
    	
    	//TODO display them on the map
    	
    }
}
