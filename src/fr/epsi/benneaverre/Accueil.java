package fr.epsi.benneaverre;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
	Button boutonInfos = null;
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
	    boutonInfos = (Button) findViewById(R.id.infosSup);
	    
	    ArrayList<Titre> menuDeroulant = new ArrayList<Titre>();
	    menuDeroulant.add(new Titre("Localiser ma position"));
	    menuDeroulant.add(new Titre("Entrer une adresse"));
	    menuDeroulant.add(new Titre("Afficher la carte globale"));
	    
	    ArrayList<SousTitre> sousTitrePosition = new ArrayList<SousTitre>();
	    sousTitrePosition.add(new SousTitre(menuDeroulant.get(0), "Sous-Titre Localisation Actuelle"));
	    
	    ArrayList<SousTitre> sousTitreAdresse = new ArrayList<SousTitre>();
	    sousTitreAdresse.add(new SousTitre(menuDeroulant.get(1), "Sous-Titre Adresse"));
	    
	    ArrayList<SousTitre> sousTitreCarte = new ArrayList<SousTitre>();
	    sousTitreCarte.add(new SousTitre(menuDeroulant.get(2), "Sous-Titre Localisation Carte"));
	    
	    /*sousTitres.add(new SousTitre(menuDeroulant.get(0), Constantes.LOCALISATION_ACTUELLE));
	    sousTitres.add(new SousTitre(menuDeroulant.get(1), Constantes.ENTRER_ADRESSE));
	    sousTitres.add(new SousTitre(menuDeroulant.get(2), Constantes.LOCALISATION_CARTE));*/
	    
	    menuDeroulant.get(0).setSousTitres(sousTitrePosition);
	    menuDeroulant.get(1).setSousTitres(sousTitreAdresse);
	    //menuDeroulant.get(2).setSousTitres(sousTitreCarte);
	    
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
	  
	    boutonInfos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Accueil.this, AffichageinfosComplementaires.class);
				startActivity(intent);
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
