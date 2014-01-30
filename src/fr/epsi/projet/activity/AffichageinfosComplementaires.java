package fr.epsi.projet.activity;

import fr.epsi.projet.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class AffichageinfosComplementaires extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_comp);
        EditText myEditText = null;
        
        myEditText = (EditText)findViewById(R.id.editText1);
        myEditText.setEnabled(false);
    } 
}
