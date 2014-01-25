package fr.epsi.projet.activity;

import java.util.ArrayList;

import org.w3c.dom.Document;

import android.app.Activity;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
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
import com.google.android.gms.maps.model.PolylineOptions;

import fr.epsi.projet.R;
import fr.epsi.projet.beans.Emplacement;
import fr.epsi.projet.common.Constantes;
import fr.epsi.projet.service.GMapV2Direction;
import fr.epsi.projet.service.ServiceRest;

public class Map extends Activity {

	// Google Map
    private GoogleMap googleMap;
    private ServiceRest serviceRest = new ServiceRest();
    private GMapV2Direction md = new GMapV2Direction();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);  
        
        try {
        	// Compatibility Thread
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            
            // Loading map
            initilizeMap();
            LatLng position = new LatLng(Constantes.LATITUDE_NANTES, Constantes.LONGITUDE_NANTES);
            //positionnement + zoom sur Nantes centre pour le moment
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
            		position).zoom(14).build();
            
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); 
            googleMap.setMyLocationEnabled(true);
                              
            
            /////////////////
            // getLocation //
            /////////////////
            
            boolean gps_enabled = false, network_enabled = false;
            LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
            Double lat, lng;
            LatLng fromPosition = null, toPosition = null;
            
            // Verify location enable (GPS/Network)
            try{
            	gps_enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
            }catch(Exception ex){}
            try{
            	network_enabled = service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            }catch(Exception ex){}
            
           
            if(gps_enabled || network_enabled) 
            {
            	// Set start Localisation 
            	Criteria criteria = new Criteria();
		        String provider = service.getBestProvider(criteria, false);
		        Location location = service.getLastKnownLocation(provider);
		        LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
		        
		        fromPosition = userLocation;
		        
		        // Set Finish Destination
		        lat = userLocation.latitude;
		        lng = userLocation.longitude;		
		        
	            Emplacement emplacement = serviceRest.getGeoBenne(lat, lng);
	            toPosition = new LatLng(emplacement.get_l()[0], emplacement.get_l()[1]);
            
	            // Set Direction
	            if(fromPosition != null && toPosition != null)
	            {
	            	MarkerOptions from = new MarkerOptions().position(fromPosition).title("Start");
	            	MarkerOptions to = new MarkerOptions().position(toPosition).title("End");
	            	to.icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_benne));
		        	googleMap.addMarker(to);
		        	googleMap.addMarker(from);
		        	
		        	Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_WALKING);
		    	
		    		ArrayList<LatLng> directionPoint = md.getDirection(doc);
		    		PolylineOptions rectLine = new PolylineOptions().width(5).color(Color.rgb(0, 100, 0));
		    		
		    		for(int i = 0 ; i < directionPoint.size() ; i++) {			
		    			rectLine.add(directionPoint.get(i));
		    		}		    				    		
		    		
		    		googleMap.addPolyline(rectLine);
	            }
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
