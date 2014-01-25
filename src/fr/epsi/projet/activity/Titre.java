package fr.epsi.projet.activity;

import java.util.ArrayList;

public class Titre {
	private String nom;
	private ArrayList<SousTitre> sousTitres;

	public Titre(String nom) {
		this.nom = nom;
		this.sousTitres = new ArrayList<SousTitre>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<SousTitre> getSousTitres() {
		return sousTitres;
	}

	public void setSousTitres(ArrayList<SousTitre> sousTitres) {
		this.sousTitres = sousTitres;
	}
}
