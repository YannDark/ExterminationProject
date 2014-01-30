package fr.epsi.projet.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import fr.epsi.projet.R;

public class MyCustomAdapter extends BaseExpandableListAdapter {

	private Context vl_context;
	private ArrayList<Titre> vl_titres;
	private LayoutInflater vl_inflater;

	public MyCustomAdapter(Context context, ArrayList<Titre> menuDeroulant) {
		this.vl_context = context;
		this.vl_titres = menuDeroulant;
		vl_inflater = LayoutInflater.from(context);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	public Object getChild(int gPosition, int cPosition) {
		return vl_titres.get(gPosition).getSousTitres().get(cPosition);
	}

	public long getChildId(int gPosition, int cPosition) {
		return cPosition;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final SousTitre vl_sousTitres = (SousTitre) getChild(groupPosition, childPosition);

		final ChildViewHolder childViewHolder;
		EditText editTest = null; 

		if (convertView == null) {
			childViewHolder = new ChildViewHolder();

			convertView = vl_inflater.inflate(R.layout.sous_titre, null);

			editTest = (EditText) convertView.findViewById(R.id.editText1);            

			childViewHolder.textViewChild = (TextView) convertView.findViewById(R.id.editText1);
			childViewHolder.buttonChild = (Button) convertView.findViewById(R.id.BTChild);

			convertView.setTag(childViewHolder);
		} else {
			childViewHolder = (ChildViewHolder) convertView.getTag();
		}

		childViewHolder.buttonChild.setText("Lancer");

		childViewHolder.buttonChild.setOnClickListener(new OnClickListener() {

			public void onClick(View v) { 
				// Retourne le texte de l'adresse
				EditText myETAdresse = (EditText) childViewHolder.textViewChild;
				String myAdresse = myETAdresse.getText().toString();
				Intent myIntent = new Intent(vl_context , Map.class);
				myIntent.putExtra("adress", myAdresse);
				vl_context.startActivity(myIntent);
			}
		});

		return convertView;
	}

	public int getChildrenCount(int gPosition) {
		return vl_titres.get(gPosition).getSousTitres().size();
	}

	public Object getGroup(int gPosition) {
		return vl_titres.get(gPosition);
	}

	public int getGroupCount() {
		return vl_titres.size();
	}

	public long getGroupId(int gPosition) {
		return gPosition;
	}

	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupViewHolder gholder;

		Titre titr = (Titre) getGroup(groupPosition);

		if (convertView == null) {
			gholder = new GroupViewHolder();

			convertView = vl_inflater.inflate(R.layout.titre, null);

			gholder.textViewGroup = (TextView) convertView.findViewById(R.id.list_item_text_view);

			convertView.setTag(gholder);
		} else {
			gholder = (GroupViewHolder) convertView.getTag();
		}

		gholder.textViewGroup.setText(titr.getNom());

		gholder.textViewGroup.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				boolean gps_enabled = false, network_enabled = false;
				LocationManager service = (LocationManager) vl_context.getSystemService(Context.LOCATION_SERVICE);
				int position = groupPosition;
				// Verify location enable (GPS/Network)
				try{
					gps_enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
				}catch(Exception ex){}
				try{
					network_enabled = service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
				}catch(Exception ex){}

				//on va sur le chemin le plus proche de l'utilisateur et la benne la plus proche 
				if(position == 1){
					if(gps_enabled || network_enabled)
					{
						Intent myIntent = new Intent(vl_context , Map.class);
						vl_context.startActivity(myIntent);
					}
					else
					{
						//Ask the user to enable GPS
						AlertDialog.Builder builder = new AlertDialog.Builder((Activity) vl_context);
						builder.setTitle("Location Manager");
						builder.setMessage("Would you like to enable GPS?");
						builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//Launch settings, allowing user to make a change
								Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								vl_context.startActivity(i);
							}
						});
						builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//No location service, no Activity
								// Doing Nothing
							}
						});
						builder.create().show();
					}
				}
				else if(position == 2){ // on affiche toutes les bennes 
					Intent myIntent = new Intent(vl_context , Maps.class);
					vl_context.startActivity(myIntent);
				}

			}
		});

		return convertView;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

	class GroupViewHolder {
		public TextView textViewGroup;
	}

	class ChildViewHolder {
		public TextView textViewChild;
		public Button buttonChild;
	}
}