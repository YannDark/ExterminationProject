package fr.epsi.benneaverre;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Accueil extends Activity {

	RelativeLayout layout = null;
	Button boutonLancer = null;
	ExpandableListView menuExpandable = null;
	TextView leTitre = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_accueil, null);
	    setContentView(layout);
	    
	    leTitre = (TextView) findViewById(R.id.textView1);
	    Typeface face = Typeface.createFromAsset(getAssets(),"font/HARLOWSI.TTF");
	    leTitre.setTypeface(face);
	
	    boutonLancer = (Button) findViewById(R.id.button1);
	    menuExpandable = (ExpandableListView) findViewById(R.id.expandableListView1);	    
	    
	    Titre menuDeroulant = new Titre();
	    menuDeroulant.setNom("Choisissez le mode d'affichage");
	    ArrayList<SousTitre> sousTitres = new ArrayList<SousTitre>();
	    
	    sousTitres.add(new SousTitre(menuDeroulant, Constantes.LOCALISATION_ACTUELLE));
	    sousTitres.add(new SousTitre(menuDeroulant, Constantes.ENTRER_ADRESSE));
	    sousTitres.add(new SousTitre(menuDeroulant, Constantes.LOCALISATION_CARTE));
	    
	    menuDeroulant.setSousTitres(sousTitres);
	    
	    MyCustomAdapter customAdapt = new MyCustomAdapter(this, menuDeroulant);
	    
	    menuExpandable.setAdapter(customAdapt);
	    
	    boutonLancer.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(menuExpandable.isShown()){
					menuExpandable.setVisibility(v.INVISIBLE);;
				} else {
					menuExpandable.setVisibility(v.VISIBLE);
				}
			}
		});
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accueil, menu);
		return true;
	}

}
