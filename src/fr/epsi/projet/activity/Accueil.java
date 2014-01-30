package fr.epsi.projet.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fr.epsi.projet.R;

public class Accueil extends Activity {

	RelativeLayout layout = null;
	TextView boutonLancer = null;
	Button boutonInfos = null;
	ExpandableListView menuExpandable = null;
	TextView leTitre = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
		if (tabletSize) {
			layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.accueil_tablette, null);
			// do something
		} else {
			layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_accueil, null);
			// do something else
		}

		setContentView(layout);

		leTitre = (TextView) findViewById(R.id.textView1);
		Typeface face = Typeface.createFromAsset(getAssets(),"font/HARLOWSI.TTF");
		leTitre.setTypeface(face);

		boutonLancer = (TextView) findViewById(R.id.button1);
		menuExpandable = (ExpandableListView) findViewById(R.id.expandableListView1);            
		boutonInfos = (Button) findViewById(R.id.infosSup);

		ArrayList<Titre> menuDeroulant = new ArrayList<Titre>();
		menuDeroulant.add(new Titre("Entrer une adresse"));
		menuDeroulant.add(new Titre("Localiser ma position"));
		menuDeroulant.add(new Titre("Afficher la carte globale"));

		ArrayList<SousTitre> sousTitreAdresse = new ArrayList<SousTitre>();
		sousTitreAdresse.add(new SousTitre(menuDeroulant.get(0), "Sous-Titre Adresse"));

		ArrayList<SousTitre> sousTitrePosition = new ArrayList<SousTitre>();
		sousTitrePosition.add(new SousTitre(menuDeroulant.get(1), "Sous-Titre Position"));

		ArrayList<SousTitre> sousTitreCarte = new ArrayList<SousTitre>();
		sousTitreCarte.add(new SousTitre(menuDeroulant.get(2), "Sous-Titre Localisation Carte"));

		menuDeroulant.get(0).setSousTitres(sousTitreAdresse);

		MyCustomAdapter customAdapt = new MyCustomAdapter(this, menuDeroulant);

		menuExpandable.setAdapter(customAdapt);

		boutonInfos.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Accueil.this, AffichageinfosComplementaires.class);
				startActivity(intent);
			}
		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}