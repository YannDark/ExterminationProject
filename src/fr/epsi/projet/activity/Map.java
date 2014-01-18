package fr.epsi.projet.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.epsi.projet.R;
import fr.epsi.projet.R.drawable;
import fr.epsi.projet.R.id;
import fr.epsi.projet.R.layout;
import fr.epsi.projet.common.Constantes;

public class Map extends Activity {

	// Google Map
    private GoogleMap googleMap;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
 
        try {
            // Loading map
            initilizeMap();
            LatLng position = new LatLng(Constantes.LATITUDE_NANTES, Constantes.LONGITUDE_NANTES);
            //positionnement + zoom sur Nantes centre pour le moment
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
            		position).zoom(12).build();
            
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); 
                        
            // create marker
            MarkerOptions marker = new MarkerOptions().position(position).title("Hello Maps ");
          //changement d'icone
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_benne));
            
            // adding marker
            googleMap.addMarker(marker);
 
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
