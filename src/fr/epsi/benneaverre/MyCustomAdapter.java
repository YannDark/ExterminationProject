package fr.epsi.benneaverre;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

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

		ChildViewHolder childViewHolder;
		EditText editTest = null; 
		
        if (convertView == null) {
        	childViewHolder = new ChildViewHolder();

            convertView = vl_inflater.inflate(R.layout.sous_titre, null);

            editTest = (EditText) convertView.findViewById(R.id.editText1);	    
            
    		/*if (vl_titres.get(groupPosition).getNom().equals("Entrer une adresse")){
    			editTest.setVisibility(View.VISIBLE);
    		}
    		else{
    			editTest.setVisibility(View.INVISIBLE);
    		}*/
            
            childViewHolder.textViewChild = (TextView) convertView.findViewById(R.id.editText1);
            childViewHolder.buttonChild = (Button) convertView.findViewById(R.id.BTChild);

            convertView.setTag(childViewHolder);
        } else {
        	childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        //childViewHolder.textViewChild.setText(vl_sousTitres.getNom());

        childViewHolder.buttonChild.setText("Lancer");

        childViewHolder.buttonChild.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(vl_context, "Groupe : " + vl_sousTitres.getTitre().getNom() + " - Bouton : " + vl_sousTitres.getNom(), Toast.LENGTH_SHORT).show();
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

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
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
