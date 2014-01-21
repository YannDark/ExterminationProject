package fr.epsi.benneaverre;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyCustomAdapter extends BaseExpandableListAdapter {
	 
	private Context vl_context;
	private Titre vl_titres;
	private LayoutInflater vl_inflater;

	public MyCustomAdapter(Context context, Titre menuDeroulant) {
		this.vl_context = context;
		this.vl_titres = menuDeroulant;
		vl_inflater = LayoutInflater.from(context);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	public Object getChild(int gPosition, int cPosition) {
		return vl_titres.getSousTitres().get(cPosition);
	}

	public long getChildId(int gPosition, int cPosition) {
		return cPosition;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final SousTitre vl_sousTitres = (SousTitre) getChild(groupPosition, childPosition);

		ChildViewHolder childViewHolder;

        if (convertView == null) {
        	childViewHolder = new ChildViewHolder();

            convertView = vl_inflater.inflate(R.layout.sous_titre, null);

            //childViewHolder.textViewChild = (TextView) convertView.findViewById(R.id.list_item_text_child);
            childViewHolder.buttonChild = (Button) convertView.findViewById(R.id.BTChild);

            convertView.setTag(childViewHolder);
        } else {
        	childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        //childViewHolder.textViewChild.setText(vl_sousTitres.getNom());

        childViewHolder.buttonChild.setText(vl_sousTitres.getNom());

        childViewHolder.buttonChild.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(vl_context, "Groupe : " + vl_sousTitres.getTitre().getNom() + " - Bouton : " + vl_sousTitres.getNom(), Toast.LENGTH_SHORT).show();
			}
		});

        return convertView;
	}

	public int getChildrenCount(int gPosition) {
		return vl_titres.getSousTitres().size();
	}

	public Object getGroup(int gPosition) {
		return vl_titres;
	}

	public int getGroupCount() {
		return 1;
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
/*public class MyCustomAdapter extends BaseExpandableListAdapter {

	private Context context;
	private LayoutInflater inflater;
    private Titre titre;
 
    public MyCustomAdapter(Context context, Titre titre) {
		this.context = context;
		this.titre = titre;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	public Object getChild(int cPosition) {
		return titre.getSousTitres().get(cPosition);
	}

	public long getChildId(int gPosition, int cPosition) {
		return cPosition;
	}

	public View getChildView( int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final SousTitre sousTitre = (SousTitre) getChild(childPosition);

		SousTitreViewHolder sousTitreViewHolder;

        if (convertView == null) {
        	sousTitreViewHolder = new SousTitreViewHolder();

            convertView = inflater.inflate(R.layout.sous_titre, null);

            sousTitreViewHolder.textViewSousTitre = (TextView) convertView.findViewById(R.id.list_item_text_child);

            convertView.setTag(sousTitreViewHolder);
        } else {
        	sousTitreViewHolder = (SousTitreViewHolder) convertView.getTag();
        }

        sousTitreViewHolder.textViewSousTitre.setText(sousTitre.getNom());

        return convertView;
	}

	public int getSousTitreCount(int gPosition) {
		return titre.getSousTitres().size();
	}

	public Object getTitre() {
		return titre;
	}
	
	public View getGroupView(boolean isExpanded, View convertView, ViewGroup parent) {
		TitreViewHolder gholder;

		Titre titre = (Titre) getTitre();

        if (convertView == null) {
        	gholder = new TitreViewHolder();

        	convertView = inflater.inflate(R.layout.titre, null);

        	gholder.textViewTitre = (TextView) convertView.findViewById(R.id.list_item_text_view);
        	gholder.buttonTitre = (Button) convertView.findViewById(R.id.button_titre);
        	
        	convertView.setTag(gholder);
        } else {
        	gholder = (TitreViewHolder) convertView.getTag();
        }

        gholder.textViewTitre.setText(titre.getNom());

        return convertView;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

	class TitreViewHolder {
		public TextView textViewTitre;
		public Button buttonTitre;
	}

	class SousTitreViewHolder {
		public TextView textViewSousTitre;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
}*/
