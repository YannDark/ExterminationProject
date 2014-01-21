package fr.epsi.benneaverre;

public class SousTitre {

	private Titre titre;
	private String nom;

	public SousTitre(Titre titre, String nom) {
		super();
		this.titre = titre;
		this.nom = nom;
	}

	public Titre getTitre() {
		return titre;
	}

	public void setTitre(Titre titre) {
		this.titre = titre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
